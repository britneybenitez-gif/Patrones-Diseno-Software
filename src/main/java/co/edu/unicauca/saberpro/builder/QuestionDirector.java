
package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionDirector {

    public Question buildPreguntaLecturaCritica(IQuestionBuilder builder) {

        return builder

                .withContext(
                        "La tecnologia ha transformado la forma en que las personas acceden a la informacion y se comunican.",
                        "Texto academico sobre tecnologia"
                )

                .withEnunciado(
                        "¿Cual es la idea principal del texto?"
                )

                .addOption(
                        "La tecnologia transforma la comunicacion y el acceso a la informacion.",
                        true
                )

                .addOption(
                        "La tecnologia unicamente sirve para entretenimiento.",
                        false
                )

                .addOption(
                        "La tecnologia elimina la necesidad de estudiar.",
                        false
                )

                .addOption(
                        "La tecnologia no influye en la sociedad.",
                        false
                )

                .withJustification(
                        "La opcion A es correcta porque resume la transformacion de la comunicacion y el acceso a la informacion."
                )

                .withClassification(
                        "Lectura Critica"
                )

                .build();
    }

    public Question buildPreguntaRazonamientoCuantitativo(IQuestionBuilder builder) {

        return builder

                .withContext(
                        "Un estudiante compra 3 cuadernos. Cada cuaderno cuesta 5000 pesos.",
                        "Situacion matematica"
                )

                .withEnunciado(
                        "¿Cuanto debe pagar el estudiante en total?"
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
