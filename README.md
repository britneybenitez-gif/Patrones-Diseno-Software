Banco de Preguntas Saber PRO

 Taller Integrado 07 y 08 – Patrones de Diseño de Software

*Universidad:* Universidad del Cauca  
*Programa:* Ingeniería de Sistemas  
*Asignatura:* Ingeniería de Software II  
*Periodo académico:* 2026.2  
*Lenguaje:* Java 17  
*Herramienta de construcción:* Apache Maven  
*Pruebas unitarias:* JUnit 5  

1. Descripción del proyecto

El *Banco de Preguntas Saber PRO* es una aplicación académica desarrollada en Java 17 que permite construir, importar, administrar y evaluar preguntas mediante la aplicación de patrones de diseño de software.
El proyecto corresponde al Taller Integrado 07 y 08 de Ingeniería de Software II de la Universidad del Cauca.
Su propósito es demostrar cómo los patrones de diseño permiten desarrollar aplicaciones modulares, extensibles y mantenibles, aplicando principios de programación orientada a objetos y buenas prácticas de ingeniería de software.

Se implementan seis patrones de diseño:

- *Builder:* construcción de preguntas.
- *Adapter:* integración de preguntas externas.
- *Facade:* simplificación de las operaciones del sistema.
- *State:* administración del ciclo de vida de las preguntas.
- *Template Method:* evaluación de respuestas.
- *Decorator:* incorporación dinámica de funcionalidades adicionales.

Además, la aplicación incluye un **cuestionario interactivo por consola**, en el cual el usuario puede responder preguntas de selección múltiple, recibir retroalimentación y consultar su calificación final.

2. Objetivos

 2.1. Objetivo general

Implementar patrones de diseño creacionales, estructurales y de comportamiento en un sistema de gestión de preguntas Saber PRO, promoviendo la separación de responsabilidades, reutilización de componentes y extensibilidad del software.

 2.2. Objetivos específicos

- Construir preguntas mediante el patrón Builder.
- Adaptar información proveniente de servicios externos mediante Adapter.
- Simplificar las operaciones del banco de preguntas mediante Facade.
- Gestionar el ciclo de vida de las preguntas utilizando State.
- Implementar algoritmos de evaluación reutilizables mediante Template Method.
- Agregar funcionalidades dinámicamente utilizando Decorator.
- Permitir al usuario responder preguntas desde la consola.
- Verificar el comportamiento del sistema mediante pruebas unitarias con JUnit 5.

 3. Patrones de diseño implementados

 3.1. Builder – Patrón creacional

Permite construir objetos complejos paso a paso, separando el proceso de construcción de la representación del objeto.

*Clases principales:*

- IQuestionBuilder
- QuestionBuilder
- QuestionDirector

*Funcionalidades:*

- Definir el contexto de una pregunta.
- Establecer su enunciado.
- Agregar cuatro opciones de respuesta.
- Especificar la respuesta correcta.
- Incorporar justificación y clasificación.
- Validar que exista exactamente una respuesta correcta.

El sistema utiliza este patrón para construir preguntas de lectura crítica y razonamiento cuantitativo.

3.2. Adapter – Patrón estructural

Permite integrar información proveniente de sistemas externos mediante la adaptación de sus interfaces y formatos.

*Clases principales:*

- QuestionProvider
- ExternalQuestionService
- ExternalQuestionAdapter

*Funcionamiento:*

`ExternalQuestionService` proporciona información de una pregunta mediante una cadena de texto delimitada.

`ExternalQuestionAdapter` interpreta los datos y utiliza Builder para convertirlos en un objeto `Question` compatible con el sistema.

Este mecanismo permite integrar preguntas sin modificar el modelo principal de la aplicación.

3.3. Facade – Patrón estructural

Proporciona una interfaz simplificada para acceder a las funcionalidades del Banco de Preguntas.

*Clase principal:*

- QuestionBankFacade

*Componentes relacionados:*

- QuestionService
- QuestionRepository
- QuestionValidator
- QuestionDirector
- ExternalQuestionAdapter

*Operaciones disponibles:*

- Crear preguntas de lectura crítica.
- Crear preguntas de razonamiento cuantitativo.
- Importar preguntas externas.
- Listar preguntas registradas.
- Consultar la cantidad de preguntas disponibles.

La fachada coordina los componentes internos y evita que la clase cliente deba gestionar cada subsistema directamente.

 3.4. State – Patrón de comportamiento

Permite modificar el comportamiento de una pregunta según su estado dentro del proceso de revisión y publicación.

*Clases principales:*

- IQuestionState
- QuestionContext
- DraftState
- PendingReviewState
- ReviewState
- ApprovedState
- PublishedState

*Ciclo de vida:*
Borrador → Pendiente de revisión → En revisión → Aprobada → Publicada
Durante la revisión también es posible solicitar correcciones, lo que permite regresar la pregunta al estado Borrador.

*Reglas implementadas:*

- Las preguntas comienzan en Borrador.
- Solamente se permiten las transiciones definidas.
- Las operaciones inválidas generan excepciones.
- Las modificaciones se restringen cuando una pregunta se encuentra fuera del estado Borrador.
- Una pregunta publicada no puede regresar mediante las transiciones normales a un estado anterior.

3.5. Template Method – Patrón de comportamiento

Define una estructura general para evaluar respuestas, permitiendo que cada tipo de evaluador implemente determinadas etapas del algoritmo.

**Clases principales:**

- QuestionEvaluator
- MultipleChoiceEvaluator
- TrueFalseEvaluator
- CaseStudyEvaluator

*Algoritmo general:*

1. Recibir la respuesta del estudiante.
2. Validar el formato de la respuesta.
3. Calcular el puntaje.
4. Generar retroalimentación.

*Tipos de evaluación implementados:*

- Selección múltiple.
- Verdadero o falso.
- Análisis de casos mediante palabras clave.

La estructura compartida evita duplicar el algoritmo principal de evaluación.

3.6. Decorator – Patrón estructural

Permite agregar nuevas responsabilidades a una pregunta mediante composición, sin modificar su clase original.

*Clases principales:*

- QuestionComponent
- BasicQuestion
- QuestionDecorator
- HighDifficultyQuestion
- FeedbackQuestion

*Funcionalidades implementadas:*

- Asignar un multiplicador de puntaje por alta dificultad.
- Añadir retroalimentación técnica.
- Combinar varios decoradores sobre una misma pregunta.

*Ejemplo:*

Una pregunta básica con puntaje de `1.0` puede recibir un decorador de alta dificultad y pasar a valer `2.0`.

Posteriormente, se puede agregar un segundo decorador para incorporar retroalimentación adicional sin modificar su puntaje.

4. Cuestionario interactivo

La aplicación incluye una interfaz por consola que permite responder preguntas de selección múltiple.

**Funcionamiento:**

1. Se construyen dos preguntas mediante Builder.
2. Se importa una pregunta externa mediante Adapter.
3. Facade proporciona el listado de preguntas.
4. El sistema presenta el contexto, el enunciado y cuatro opciones identificadas con A, B, C y D.
5. El usuario escribe la letra de su respuesta.
6. Template Method evalúa la respuesta y genera retroalimentación.
7. Al finalizar se muestra el puntaje total y el porcentaje de aciertos.

*Ejemplo de interacción:*

```text
========== BANCO DE PREGUNTAS SABER PRO ==========

PREGUNTA 1 DE 3

¿Cuál es la idea principal del texto?

A. La tecnología transforma la comunicación
   y el acceso a la información.
B. La tecnología únicamente sirve para entretenimiento.
C. La tecnología elimina la necesidad de estudiar.
D. La tecnología no influye en la sociedad.

Seleccione su respuesta (A, B, C o D): A

--- RESULTADO ---

Respuesta correcta. Obtuvo 1 punto.

Puntaje obtenido: 1.0
```

*Al finalizar el cuestionario:*

```text
============= RESULTADO FINAL =============

Total de preguntas: 3
Respuestas correctas: 2
Respuestas incorrectas: 1

Puntaje total: 2.0 / 3
Porcentaje de aciertos: 66.67%
```

Los valores anteriores son ejemplos ilustrativos. El resultado real depende de las respuestas del usuario.

Después del cuestionario, la aplicación también ejecuta demostraciones de los patrones State, Template Method y Decorator.

5. Arquitectura del proyecto

El sistema utiliza una organización modular por paquetes, separando los modelos de dominio, patrones de diseño y componentes de infraestructura.

```text
Patrones-Diseno-Software/
│
├── pom.xml
├── README.md
├── .gitignore
│
└── src/
    ├── main/
    │   └── java/
    │       └── co/edu/unicauca/saberpro/
    │           │
    │           ├── ApplicationMain.java
    │           │
    │           ├── domain/
    │           │   └── model/
    │           │
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
                ├── QuestionEvaluatorTest.java
                ├── CaseStudyEvaluatorTest.java
                └── QuestionDecoratorTest.java
```

*Responsabilidades principales:*

| Paquete | Responsabilidad |
|---|---|
| `domain.model` | Entidades del sistema |
| `builder` | Construcción de preguntas |
| `adapter` | Integración de preguntas externas |
| `facade` | Interfaz simplificada |
| `state` | Ciclo de vida de las preguntas |
| `templatemethod` | Evaluación de respuestas |
| `decorator` | Funcionalidades dinámicas |
| `repository` | Almacenamiento en memoria |
| `service` | Coordinación de operaciones |
| `validation` | Validación de preguntas |

Las preguntas se almacenan temporalmente en memoria. La aplicación no utiliza una base de datos persistente.

 6. Tecnologías utilizadas

| Tecnología | Función |
|---|---|
| Java 17 | Lenguaje principal |
| Apache Maven | Compilación y dependencias |
| JUnit 5 | Pruebas unitarias |
| Git | Control de versiones |
| GitHub | Alojamiento del repositorio |
| Visual Studio Code | Entorno de desarrollo |

 7. Requisitos del sistema

Para ejecutar el proyecto se necesita:

- Java JDK 17.
- Apache Maven.
- Git, si se desea clonar el repositorio.
- Una terminal que permita introducir respuestas por teclado.

**Verificar las herramientas instaladas:**

```bash
java -version
mvn -version
git --version
```

 8. Instalación y ejecución

 Paso 1. Clonar el repositorio

```bash
git clone https://github.com/britneybenitez-gif/Patrones-Diseno-Software.git
```

 Paso 2. Entrar al proyecto

```bash
cd Patrones-Diseno-Software
```

 Paso 3. Compilar

```bash
mvn clean compile
```
 Paso 4. Ejecutar la aplicación

```bash
mvn exec:java
```

La aplicación presentará las preguntas disponibles.

Cuando aparezca el mensaje:

```text
Seleccione su respuesta (A, B, C o D):
```

Escriba la letra seleccionada y presione Enter.

El programa evaluará la respuesta y continuará con la siguiente pregunta.

### Nota sobre codificación

Los archivos fuente utilizan UTF-8 para conservar correctamente las tildes, signos de interrogación y demás caracteres especiales del español.

Se recomienda ejecutar el programa en una terminal compatible con UTF-8.

La visualización correcta de los caracteres también depende de la configuración del entorno donde se ejecute la aplicación.

 9. Pruebas unitarias

El proyecto incorpora pruebas automatizadas utilizando JUnit 5.

*Archivos de pruebas:*

- QuestionBuilderTest.java
- ExternalQuestionAdapterTest.java
- QuestionBankFacadeTest.java
- QuestionStateTest.java
- QuestionEvaluatorTest.java
- CaseStudyEvaluatorTest.java
- QuestionDecoratorTest.java

Las pruebas verifican:

- Construcción válida e inválida de preguntas.
- Validación de opciones y respuestas correctas.
- Adaptación de preguntas externas.
- Registro y consulta mediante Facade.
- Transiciones válidas e inválidas de State.
- Restricciones de edición.
- Evaluación de respuestas.
- Incorporación y combinación de decoradores.

*Ejecutar todas las pruebas:*

```bash
mvn clean test
```

*Ejecutar solamente las pruebas de State:*

```bash
mvn -Dtest=QuestionStateTest test
```

*Ejecutar solamente las pruebas de Decorator:*

```bash
mvn -Dtest=QuestionDecoratorTest test
```

*Resultado obtenido durante las verificaciones del desarrollo:*

```text
Tests run: 48, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

 10. Principios de diseño aplicados

La aplicación utiliza encapsulamiento, abstracción, herencia, polimorfismo y composición.

También aplica principios SOLID:

*Responsabilidad única (SRP):** separa las tareas de construcción, validación, almacenamiento y evaluación.

*Abierto/Cerrado (OCP):** permite extender funcionalidades mediante nuevos decoradores y evaluadores.

*Inversión de dependencias (DIP):** utiliza interfaces y abstracciones para desacoplar diversos componentes.

 11. Repositorio del proyecto

*GitHub:*

https://github.com/britneybenitez-gif/Patrones-Diseno-Software

12. Autores

Britney Benítez Ordóñez
Yineidy Mamián Jiménez

Universidad del Cauca  
Programa de Ingeniería de Sistemas  
Ingeniería de Software II  
Periodo académico 2026.2
