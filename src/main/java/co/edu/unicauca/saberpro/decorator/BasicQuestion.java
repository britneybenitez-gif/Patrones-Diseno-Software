
package co.edu.unicauca.saberpro.decorator;

import co.edu.unicauca.saberpro.domain.model.Question;

public class BasicQuestion implements QuestionComponent {

    private final Question question;

    public BasicQuestion(Question question) {

        if (question == null) {
            throw new IllegalArgumentException(
                    "La pregunta no puede ser nula."
            );
        }

        this.question = question;
    }

    @Override
    public String getEnunciado() {
        return question.getEnunciado();
    }

    @Override
    public double getPuntaje() {
        return 1.0;
    }

    @Override
    public String getRetroalimentacion() {

        if (question.getJustificacion() != null) {
            return question.getJustificacion().getTexto();
        }

        return "Sin retroalimentacion.";
    }
}
