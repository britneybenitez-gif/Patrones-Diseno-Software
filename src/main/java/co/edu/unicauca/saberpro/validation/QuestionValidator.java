
package co.edu.unicauca.saberpro.validation;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionValidator {

    public void validar(Question pregunta) {

        if (pregunta == null) {
            throw new IllegalArgumentException(
                "La pregunta no puede ser nula."
            );
        }

        if (pregunta.getEnunciado() == null
                || pregunta.getEnunciado().isBlank()) {

            throw new IllegalArgumentException(
                "El enunciado no puede estar vacio."
            );
        }

        if (pregunta.getOpciones() == null
                || pregunta.getOpciones().size() != 4) {

            throw new IllegalArgumentException(
                "La pregunta debe tener exactamente cuatro opciones."
            );
        }

        long correctas = pregunta.getOpciones()
                .stream()
                .filter(Option::isCorrecta)
                .count();

        if (correctas != 1) {
            throw new IllegalArgumentException(
                "La pregunta debe tener una sola respuesta correcta."
            );
        }
    }
}
