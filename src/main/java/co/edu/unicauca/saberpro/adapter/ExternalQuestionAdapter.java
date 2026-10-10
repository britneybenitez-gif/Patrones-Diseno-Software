
package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;

public class ExternalQuestionAdapter implements QuestionProvider {

    private final ExternalQuestionService service;

    public ExternalQuestionAdapter(ExternalQuestionService service) {
        this.service = service;
    }

    @Override
    public Question getQuestion() {

        // 1. Obtener la información externa
        String rawData = service.getQuestionData();

        // 2. Separar los campos
        String[] datos = rawData.split("\\|", -1);

        // 3. Validar el formato externo
        if (datos.length != 10) {
            throw new IllegalArgumentException(
                    "El formato de la pregunta externa es inválido."
            );
        }

        // 4. Identificar la respuesta correcta
        int respuestaCorrecta;

        try {
            respuestaCorrecta = Integer.parseInt(datos[7]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "La respuesta correcta debe ser un número."
            );
        }

        if (respuestaCorrecta < 0 || respuestaCorrecta > 3) {
            throw new IllegalArgumentException(
                    "El índice de respuesta correcta debe estar entre 0 y 3."
            );
        }

        // 5. Construir la pregunta usando Builder
        IQuestionBuilder builder = new QuestionBuilder();

        return builder
                .withContext(datos[0], datos[1])
                .withEnunciado(datos[2])
                .addOption(datos[3], respuestaCorrecta == 0)
                .addOption(datos[4], respuestaCorrecta == 1)
                .addOption(datos[5], respuestaCorrecta == 2)
                .addOption(datos[6], respuestaCorrecta == 3)
                .withJustification(datos[8])
                .withClassification(datos[9])
                .build();
    }
}
