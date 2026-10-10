
package co.edu.unicauca.saberpro;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;
import co.edu.unicauca.saberpro.state.QuestionContext;

import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;

import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;

public class ApplicationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("       BANCO DE PREGUNTAS SABER PRO");
        System.out.println("          TALLER 07 Y 08");
        System.out.println("======================================");

        // ==========================================
        // 1. BUILDER Y ADAPTER
        // ==========================================

        QuestionBankFacade facade = new QuestionBankFacade();

        facade.crearPreguntaLecturaCritica();
        facade.crearPreguntaRazonamientoCuantitativo();
        facade.importarPreguntaExterna();

        // ==========================================
        // 2. FACADE
        // ==========================================

        List<Question> preguntas = facade.listarPreguntas();

        System.out.println(
                "\nPreguntas disponibles: "
                + facade.contarPreguntas()
        );

        // ==========================================
        // 3. CUESTIONARIO INTERACTIVO
        // ==========================================

        System.out.println("\n======================================");
        System.out.println("       INICIO DEL CUESTIONARIO");
        System.out.println("======================================");

        System.out.println(
                "\nInstrucciones: lea cada pregunta "
                + "y responda A, B, C o D."
        );

        double puntajeTotal = 0;
        int respuestasCorrectas = 0;

        for (int i = 0; i < preguntas.size(); i++) {

            Question pregunta = preguntas.get(i);

            System.out.println("\n--------------------------------------");
            System.out.println(
                    "PREGUNTA " + (i + 1)
                    + " DE " + preguntas.size()
            );
            System.out.println("--------------------------------------");

            // Mostrar contexto
            if (pregunta.getContext() != null) {

                System.out.println(
                        "\nContexto: "
                        + pregunta.getContext().getTexto()
                );
            }

            // Mostrar enunciado
            System.out.println(
                    "\n" + pregunta.getEnunciado()
            );

            // Mostrar opciones A, B, C y D
            List<Option> opciones = pregunta.getOpciones();

            String respuestaCorrecta = "";

            for (int j = 0; j < opciones.size(); j++) {

                String letra = String.valueOf(
                        (char) ('A' + j)
                );

                Option opcion = opciones.get(j);

                System.out.println(
                        letra + ". " + opcion.getTexto()
                );

                if (opcion.isCorrecta()) {
                    respuestaCorrecta = letra;
                }
            }

            // ==========================================
            // EVALUADOR - TEMPLATE METHOD
            // ==========================================

            MultipleChoiceEvaluator evaluador =
                    new MultipleChoiceEvaluator(
                            respuestaCorrecta
                    );

            String respuestaUsuario;

            while (true) {

                System.out.print(
                        "\nSeleccione su respuesta (A, B, C o D): "
                );

                if (!scanner.hasNextLine()) {

                    System.out.println(
                            "\nEntrada finalizada. "
                            + "Se cancela el cuestionario."
                    );

                    return;
                }

                respuestaUsuario = scanner
                        .nextLine()
                        .trim()
                        .toUpperCase(Locale.ROOT);

                if (respuestaUsuario.matches("[A-D]")) {
                    break;
                }

                System.out.println(
                        "Respuesta inválida. "
                        + "Escriba solamente A, B, C o D."
                );
            }

            // Evaluar respuesta
            double puntaje =
                    evaluador.evaluarRespuesta(respuestaUsuario);

            puntajeTotal += puntaje;

            if (puntaje == 1.0) {
                respuestasCorrectas++;
            }

            System.out.println("\n--- RESULTADO ---");

            System.out.println(
                    evaluador.getRetroalimentacion()
            );

            System.out.println(
                    "Puntaje obtenido: " + puntaje
            );

            if (pregunta.getJustificacion() != null) {

                System.out.println(
                        "Justificación: "
                        + pregunta.getJustificacion().getTexto()
                );
            }
        }

        // ==========================================
        // 4. RESULTADO FINAL DEL CUESTIONARIO
        // ==========================================

        int totalPreguntas = preguntas.size();

        double porcentaje = totalPreguntas == 0
                ? 0
                : (puntajeTotal / totalPreguntas) * 100;

        System.out.println("\n======================================");
        System.out.println("         RESULTADO FINAL");
        System.out.println("======================================");

        System.out.println(
                "Total de preguntas: " + totalPreguntas
        );

        System.out.println(
                "Respuestas correctas: " + respuestasCorrectas
        );

        System.out.println(
                "Respuestas incorrectas: "
                + (totalPreguntas - respuestasCorrectas)
        );

        System.out.println(
                "Puntaje total: " + puntajeTotal
                + " / " + totalPreguntas
        );

        System.out.printf(
                Locale.US,
                "Porcentaje de aciertos: %.2f%%%n",
                porcentaje
        );

        // ==========================================
        // 5. PATRON STATE
        // ==========================================

        System.out.println("\n======================================");
        System.out.println("       DEMOSTRACIÓN DEL PATRÓN STATE");
        System.out.println("======================================");

        if (!preguntas.isEmpty()) {

            QuestionContext contextoEstado =
                    new QuestionContext(preguntas.get(0));

            System.out.println(
                    "Estado inicial: "
                    + contextoEstado.getNombreEstado()
            );

            // Probar una operación no permitida
            try {

                contextoEstado.publish();

            } catch (IllegalStateException e) {

                System.out.println(
                        "Operación rechazada: "
                        + e.getMessage()
                );
            }

            // Realizar transiciones válidas
            contextoEstado.submitForReview();

            System.out.println(
                    "Estado: " + contextoEstado.getNombreEstado()
            );

            contextoEstado.startReview();

            System.out.println(
                    "Estado: " + contextoEstado.getNombreEstado()
            );

            contextoEstado.approve();

            System.out.println(
                    "Estado: " + contextoEstado.getNombreEstado()
            );

            contextoEstado.publish();

            System.out.println(
                    "Estado final: "
                    + contextoEstado.getNombreEstado()
            );
        }

        // ==========================================
        // 6. TEMPLATE METHOD - VERDADERO/FALSO
        // ==========================================

        System.out.println("\n======================================");
        System.out.println("     DEMOSTRACIÓN VERDADERO/FALSO");
        System.out.println("======================================");

        TrueFalseEvaluator evaluadorVF =
                new TrueFalseEvaluator(true);

        double puntajeVF =
                evaluadorVF.evaluarRespuesta("Verdadero");

        System.out.println(
                "Respuesta de ejemplo: Verdadero"
        );

        System.out.println(
                "Puntaje: " + puntajeVF
        );

        System.out.println(
                evaluadorVF.getRetroalimentacion()
        );

        // ==========================================
        // 7. PATRON DECORATOR
        // ==========================================

        System.out.println("\n======================================");
        System.out.println("      DEMOSTRACIÓN DEL DECORATOR");
        System.out.println("======================================");

        if (!preguntas.isEmpty()) {

            // Pregunta básica
            QuestionComponent preguntaBasica =
                    new BasicQuestion(preguntas.get(0));

            System.out.println(
                    "\nPuntaje básico: "
                    + preguntaBasica.getPuntaje()
            );

            // Aplicar decorador de alta dificultad
            QuestionComponent preguntaDificil =
                    new HighDifficultyQuestion(
                            preguntaBasica,
                            2.0
                    );

            System.out.println(
                    "Puntaje alta dificultad: "
                    + preguntaDificil.getPuntaje()
            );

            // Aplicar decorador de retroalimentación
            QuestionComponent preguntaDecorada =
                    new FeedbackQuestion(
                            preguntaDificil,
                            "Esta pregunta requiere análisis "
                            + "e interpretación."
                    );

            System.out.println(
                    "Puntaje con dos decoradores: "
                    + preguntaDecorada.getPuntaje()
            );

            System.out.println(
                    "Retroalimentación: "
                    + preguntaDecorada.getRetroalimentacion()
            );
        }

        // ==========================================
        // 8. FINALIZAR APLICACIÓN
        // ==========================================

        System.out.println("\n======================================");
        System.out.println("          FIN DE LA APLICACIÓN");
        System.out.println("======================================");

        scanner.close();
    }
}
