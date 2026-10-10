
package co.edu.unicauca.saberpro;

import java.util.List;

import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;
import co.edu.unicauca.saberpro.state.QuestionContext;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;

public class ApplicationMain {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("    BANCO DE PREGUNTAS SABER PRO");
        System.out.println("       TALLER INTEGRADO 07 Y 08");
        System.out.println("====================================");

        // ==========================================
        // 1. PATRON BUILDER
        // ==========================================

        QuestionBankFacade facade = new QuestionBankFacade();

        System.out.println("\n1. CREACION DE PREGUNTAS - BUILDER");

        facade.crearPreguntaLecturaCritica();
        facade.crearPreguntaRazonamientoCuantitativo();

        System.out.println("Preguntas creadas correctamente.");

        // ==========================================
        // 2. PATRON ADAPTER
        // ==========================================

        System.out.println("\n2. IMPORTACION EXTERNA - ADAPTER");

        Question externa = facade.importarPreguntaExterna();

        System.out.println("Pregunta importada correctamente:");
        System.out.println(externa);

        // ==========================================
        // 3. PATRON FACADE
        // ==========================================

        System.out.println("\n3. LISTADO DE PREGUNTAS - FACADE");

        List<Question> preguntas = facade.listarPreguntas();

        int numero = 1;

        for (Question pregunta : preguntas) {

            System.out.println("\nPregunta #" + numero);
            System.out.println(pregunta);

            numero++;
        }

        // ==========================================
        // 4. ESTADISTICAS DEL BANCO
        // ==========================================

        System.out.println("\n4. ESTADISTICAS DEL BANCO");

        System.out.println(
                "Total de preguntas: " + facade.contarPreguntas()
        );

        // ==========================================
        // 5. PATRON STATE
        // ==========================================

        System.out.println("\n5. DEMOSTRACION DEL PATRON STATE");

        Question preguntaState = preguntas.get(0);

        QuestionContext contextoEstado =
                new QuestionContext(preguntaState);

        System.out.println(
                "\nEstado inicial: " + contextoEstado.getNombreEstado()
        );

        System.out.println("\nPrueba de operacion no permitida:");

        try {

            contextoEstado.publish();

        } catch (IllegalStateException e) {

            System.out.println(
                    "Operacion rechazada: " + e.getMessage()
            );
        }

        System.out.println("\nIniciando ciclo de vida:");

        contextoEstado.submitForReview();

        System.out.println(
                "Estado actual: " + contextoEstado.getNombreEstado()
        );

        contextoEstado.startReview();

        System.out.println(
                "Estado actual: " + contextoEstado.getNombreEstado()
        );

        contextoEstado.approve();

        System.out.println(
                "Estado actual: " + contextoEstado.getNombreEstado()
        );

        contextoEstado.publish();

        System.out.println(
                "Estado final: " + contextoEstado.getNombreEstado()
        );

        // ==========================================
        // 6. TEMPLATE METHOD - SELECCION MULTIPLE
        // ==========================================

        System.out.println("\n6. PATRON TEMPLATE METHOD");
        System.out.println("EVALUACION DE SELECCION MULTIPLE");

        MultipleChoiceEvaluator evaluador =
                new MultipleChoiceEvaluator("B");

        double puntajeCorrecto =
                evaluador.evaluarRespuesta("B");

        System.out.println("\nRespuesta del estudiante: B");
        System.out.println("Puntaje: " + puntajeCorrecto);

        System.out.println(
                "Retroalimentacion: "
                        + evaluador.getRetroalimentacion()
        );

        double puntajeIncorrecto =
                evaluador.evaluarRespuesta("A");

        System.out.println("\nRespuesta del estudiante: A");
        System.out.println("Puntaje: " + puntajeIncorrecto);

        System.out.println(
                "Retroalimentacion: "
                        + evaluador.getRetroalimentacion()
        );

        // ==========================================
        // 7. TEMPLATE METHOD - VERDADERO / FALSO
        // ==========================================

        System.out.println("\n7. EVALUACION VERDADERO/FALSO");

        TrueFalseEvaluator evaluadorVF =
                new TrueFalseEvaluator(true);

        double puntajeVF1 =
                evaluadorVF.evaluarRespuesta("Verdadero");

        System.out.println("\nRespuesta del estudiante: Verdadero");
        System.out.println("Puntaje: " + puntajeVF1);

        System.out.println(
                "Retroalimentacion: "
                        + evaluadorVF.getRetroalimentacion()
        );

        double puntajeVF2 =
                evaluadorVF.evaluarRespuesta("Falso");

        System.out.println("\nRespuesta del estudiante: Falso");
        System.out.println("Puntaje: " + puntajeVF2);

        System.out.println(
                "Retroalimentacion: "
                        + evaluadorVF.getRetroalimentacion()
        );

        // ==========================================
        // 8. PATRON DECORATOR
        // ==========================================

        System.out.println("\n8. DEMOSTRACION DEL PATRON DECORATOR");

        Question preguntaOriginal = preguntas.get(0);

        // Crear pregunta basica
        QuestionComponent preguntaBasica =
                new BasicQuestion(preguntaOriginal);

        System.out.println("\n--- PREGUNTA BASICA ---");

        System.out.println(
                "Enunciado: " + preguntaBasica.getEnunciado()
        );

        System.out.println(
                "Puntaje: " + preguntaBasica.getPuntaje()
        );

        System.out.println(
                "Retroalimentacion: "
                        + preguntaBasica.getRetroalimentacion()
        );

        // ==========================================
        // DECORADOR DE ALTA DIFICULTAD
        // ==========================================

        QuestionComponent preguntaDificil =
                new HighDifficultyQuestion(
                        preguntaBasica,
                        2.0
                );

        System.out.println("\n--- PREGUNTA DE ALTA DIFICULTAD ---");

        System.out.println(
                "Enunciado: " + preguntaDificil.getEnunciado()
        );

        System.out.println(
                "Puntaje: " + preguntaDificil.getPuntaje()
        );

        System.out.println(
                "Retroalimentacion: "
                        + preguntaDificil.getRetroalimentacion()
        );

        // ==========================================
        // DECORADOR DE RETROALIMENTACION
        // ==========================================

        QuestionComponent preguntaDecorada =
                new FeedbackQuestion(
                        preguntaDificil,
                        "Esta pregunta requiere interpretar "
                        + "correctamente la idea principal del texto."
                );

        System.out.println("\n--- PREGUNTA CON DOS DECORADORES ---");

        System.out.println(
                "Enunciado: " + preguntaDecorada.getEnunciado()
        );

        System.out.println(
                "Puntaje final: " + preguntaDecorada.getPuntaje()
        );

        System.out.println(
                "Retroalimentacion completa: "
                        + preguntaDecorada.getRetroalimentacion()
        );

        // ==========================================
        // 9. RESULTADO FINAL
        // ==========================================

        System.out.println("\n====================================");
        System.out.println("       EJECUCION FINALIZADA");
        System.out.println("====================================");

    } // Fin del metodo main

} // Fin de ApplicationMain
