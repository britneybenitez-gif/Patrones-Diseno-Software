
package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionDirector {

    public Question buildPreguntaLecturaCritica(IQuestionBuilder builder) {

        return builder

                .withContext(
                        "La tecnología ha transformado la forma en que las personas acceden a la información y se comunican.",
                        "Texto académico sobre tecnología"
                )

                .withEnunciado(
                        "¿Cuál es la idea principal del texto?"
                )

                .addOption(
                        "La tecnología transforma la comunicación y el acceso a la información.",
                        true
                )

                .addOption(
                        "La tecnología únicamente sirve para entretenimiento.",
                        false
                )

                .addOption(
                        "La tecnología elimina la necesidad de estudiar.",
                        false
                )

                .addOption(
                        "La tecnología no influye en la sociedad.",
                        false
                )

                .withJustification(
                        "La opción A es correcta porque resume la transformación de la comunicación y el acceso a la información."
                )

                .withClassification(
                        "Lectura Crítica"
                )

                .build();
    }

    public Question buildPreguntaRazonamientoCuantitativo(IQuestionBuilder builder) {

        return builder

                .withContext(
                        "Un estudiante compra 3 cuadernos. Cada cuaderno cuesta 5000 pesos.",
                        "Situación matemática"
                )

                .withEnunciado(
                        "¿Cuánto debe pagar el estudiante en total?"
                )

                .addOption(
                        "10000 pesos",
                        false
                )

                .addOption(
                        "12000 pesos",
                        false
                )

                .addOption(
                        "15000 pesos",
                        true
                )

                .addOption(
                        "20000 pesos",
                        false
                )

                .withJustification(
                        "La respuesta correcta es 15000 porque 3 multiplicado por 5000 es igual a 15000."
                )

                .withClassification(
                        "Razonamiento Cuantitativo"
                )

                .build();
    }
}
