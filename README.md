Banco de Preguntas Saber PRO
Taller Integrado 07 y 08 – Patrones de Diseño de Software

Universidad del Cauca
Programa: Ingeniería de Sistemas  
Asignatura: Ingeniería de Software II  
Lenguaje: Java 17  
Herramienta de construcción:Apache Maven  
Pruebas unitarias:JUnit 5


 1. Descripción del proyecto

Este proyecto desarrolla un sistema académico de gestión de preguntas tipo Saber PRO mediante la implementación de patrones de diseño de software.
El objetivo es aplicar principios de diseño orientado a objetos para construir una solución modular, extensible y mantenible, que permita crear, importar, administrar y evaluar preguntas.
La aplicación integra seis patrones de diseño: **Builder, Adapter, Facade, State, Template Method y Decorator**.
El proyecto se desarrolla con Java 17 y Maven, incorpora pruebas unitarias con JUnit 5 y utiliza una organización de paquetes que separa las responsabilidades de cada componente.

 
 2. Objetivos

Objetivo general

Implementar patrones de diseño creacionales, estructurales y de comportamiento en un sistema de gestión de preguntas Saber PRO.

Objetivos específicos

- Construir preguntas complejas mediante el patrón Builder.
- Integrar información procedente de un servicio externo mediante Adapter.
- Simplificar las operaciones del sistema utilizando Facade.
- Administrar el ciclo de vida de las preguntas mediante State.
- Implementar diferentes estrategias de evaluación utilizando Template Method.
- Incorporar funcionalidades adicionales mediante Decorator.
- Verificar el comportamiento de los componentes mediante pruebas unitarias.

3. Patrones de diseño implementados

3.1. Builder – Patrón creacional

Permite construir preguntas paso a paso, separando su proceso de construcción de su representación.

*Clases principales:*
- IQuestionBuilder
- QuestionBuilder
- QuestionDirector

**Funcionalidades:**
- Construcción de preguntas con contexto, enunciado, opciones, justificación y clasificación.
- Validación de exactamente cuatro opciones de respuesta.
- Verificación de una única respuesta correcta.
- Construcción de ejemplos de lectura crítica y razonamiento cuantitativo.

 3.2. Adapter – Patrón estructural

Permite integrar preguntas provenientes de un servicio externo, transformando su formato al modelo utilizado por la aplicación.

Clases principales:
- QuestionProvider
- ExternalQuestionService
- ExternalQuestionAdapter

**Funcionamiento:**

El servicio externo proporciona datos de una pregunta en formato de texto delimitado. El adaptador interpreta estos datos y utiliza Builder para construir un objeto `Question` compatible con el sistema.

### 3.3. Facade – Patrón estructural

Proporciona una interfaz simplificada para interactuar con los componentes internos del Banco de Preguntas.

*Clase principal:*
- QuestionBankFacade

**Componentes relacionados:**
- QuestionService
- QuestionRepository
- QuestionValidator
- QuestionDirector
- ExternalQuestionAdapter

**Operaciones disponibles:**
- Crear preguntas de lectura crítica.
- Crear preguntas de razonamiento cuantitativo.
- Importar preguntas externas.
- Listar preguntas registradas.
- Consultar la cantidad de preguntas.

La fachada coordina los subsistemas, evitando que el cliente deba interactuar directamente con cada componente.

3.4. State – Patrón de comportamiento

Administra el ciclo de vida de las preguntas mediante objetos que representan sus estados.

**Clases principales:**
- QuestionContext
- IQuestionState
- DraftState
- PendingReviewState
- ReviewState
- ApprovedState
- PublishedState

*Ciclo de vida:*

Borrador → Pendiente de revisión → En revisión → Aprobada → Publicada

Durante la revisión también se pueden solicitar correcciones, regresando la pregunta al estado Borrador.

Cada estado determina las operaciones permitidas y rechaza las transiciones inválidas.

Se incorporan restricciones para impedir modificaciones de las preguntas cuando se encuentran fuera del estado Borrador.

 3.5. Template Method – Patrón de comportamiento

Define un algoritmo general de evaluación y permite que sus subclases implementen determinadas etapas.

**Clases principales:**
- QuestionEvaluator
- MultipleChoiceEvaluator
- TrueFalseEvaluator
- CaseStudyEvaluator

**Etapas del algoritmo:**
1. Recibir la respuesta del estudiante.
2. Validar el formato.
3. Calcular el puntaje.
4. Generar retroalimentación.

**Tipos de evaluación:**
- Selección múltiple.
- Verdadero o falso.
- Análisis de casos mediante palabras clave.

3.6. Decorator – Patrón estructural

Permite agregar funcionalidades a las preguntas de manera dinámica, utilizando composición y sin modificar su implementación original.

**Clases principales:**
- QuestionComponent
- BasicQuestion
- QuestionDecorator
- HighDifficultyQuestion
- FeedbackQuestion

*Funcionalidades:*
- Agregar un multiplicador de puntaje por alta dificultad.
- Incorporar retroalimentación técnica adicional.
- Combinar varios decoradores sobre una misma pregunta.

Por ejemplo, una pregunta básica con puntaje de `1.0` puede convertirse en una pregunta de alta dificultad con puntaje de `2.0`.

4. Arquitectura del proyecto

El sistema está organizado en paquetes según las responsabilidades de cada componente.

```text
src/
├── main/
│   └── java/
│       └── co/edu/unicauca/saberpro/
│           ├── ApplicationMain.java
│           ├── domain/model/
│           ├── builder/
│           ├── adapter/
│           ├── facade/
│           ├── state/
│           ├── templatemethod/
│           ├── decorator/
│           ├── repository/
│           ├── service/
│           └── validation/
│
└── test/
    └── java/
        └── co/edu/unicauca/saberpro/
            ├── QuestionBuilderTest.java
            ├── ExternalQuestionAdapterTest.java
            ├── QuestionBankFacadeTest.java
            ├── QuestionStateTest.java
            ├── CaseStudyEvaluatorTest.java
            ├── QuestionEvaluatorTest.java
            └── QuestionDecoratorTest.java
```

La clase `ApplicationMain` ejecuta demostraciones de los patrones implementados.
El repositorio almacena preguntas en memoria, por lo que la información no persiste después de finalizar la aplicación.

5. Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 17 | Desarrollo de la aplicación |
| Apache Maven | Gestión de dependencias y compilación |
| JUnit 5 | Pruebas unitarias |
| Git | Control de versiones |
| GitHub | Repositorio del proyecto |
| Visual Studio Code | Entorno de desarrollo |

6. Requisitos para ejecutar el proyecto

Es necesario contar con:

- JDK 17 o superior compatible con el proyecto.
- Apache Maven.
- Git, para clonar el repositorio.
- Un editor o IDE compatible con Java.

Verificar las instalaciones:

```bash
java -version
mvn -version
git --version
```

7. Instalación y ejecución

Paso 1. Clonar el repositorio

```bash
git clone https://github.com/britneybenitez-gif/Patrones-Diseno-Software.git
```

Paso 2. Ingresar al proyecto

```bash
cd Patrones-Diseno-Software
```

Paso 3. Compilar el proyecto

```bash
mvn clean compile
```

Paso 4. Ejecutar la aplicación

```bash
mvn exec:java
```

La aplicación mostrará en consola las demostraciones de los patrones de diseño implementados.

8. Pruebas unitarias

El proyecto utiliza JUnit 5 para verificar el comportamiento de los componentes.

Las pruebas comprueban aspectos como:

- Construcción correcta e incorrecta de preguntas.
- Adaptación de información externa.
- Operaciones de la fachada.
- Transiciones válidas e inválidas de estados.
- Restricciones de modificación de preguntas.
- Evaluación de diferentes tipos de respuestas.
- Aplicación y combinación de decoradores.

Para ejecutar las pruebas:

```bash
mvn clean test
```

**Resultado de la verificación local realizada durante el desarrollo:**

- 48 pruebas ejecutadas.
- 0 fallos.
- 0 errores.
- `BUILD SUCCESS`.

9. Principios de diseño aplicados

La implementación utiliza conceptos de programación orientada a objetos como encapsulamiento, abstracción, herencia, polimorfismo y composición.

También favorece principios SOLID, especialmente:

- Responsabilidad única (SRP):separación de construcción, validación, almacenamiento y evaluación.
- Abierto/Cerrado (OCP):incorporación de nuevos decoradores y evaluadores mediante extensión.
- Inversión de dependencias (DIP):utilización de interfaces y abstracciones en diferentes componentes.

10. Repositorio del proyecto

GitHub:
https://github.com/britneybenitez-gif/Patrones-Diseno-Software

11. Autores

Integrantes: Britney Benitez Ordoñez y Yineidy Mamian Jimenez.

Universidad del Cauca
Ingeniería de Sistemas  
Ingeniería de Software II  
Periodo académico 2026.2
