
package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Classification;
import co.edu.unicauca.saberpro.domain.model.Context;
import co.edu.unicauca.saberpro.domain.model.Justification;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionBuilder implements IQuestionBuilder {

    private Question question;

    // Constructor
    public QuestionBuilder() {
        this.question = new Question();
    }

    // Agregar contexto
    @Override
    public IQuestionBuilder withContext(String texto, String fuente) {

        question.setContext(
                new Context(texto, fuente)
        );

        return this;
    }

    // Agregar enunciado
    @Override
    public IQuestionBuilder withEnunciado(String enunciado) {

        question.setEnunciado(enunciado);

        return this;
    }

    // Agregar una opción
    @Override
    public IQuestionBuilder addOption(String texto, boolean esCorrecta) {

        question.getOpciones().add(
                new Option(texto, esCorrecta)
        );

        return this;
    }

    // Agregar justificación
    @Override
    public IQuestionBuilder withJustification(String texto) {

        question.setJustificacion(
                new Justification(texto)
        );

        return this;
    }

    // Agregar clasificación
    @Override
    public IQuestionBuilder withClassification(String categoria) {

        question.setClasificacion(
                new Classification(categoria)
        );

        return this;
    }

    // Construir y validar la pregunta
    @Override
    public Question build() {

        // Validar que existan exactamente 4 opciones
        if (question.getOpciones().size() != 4) {

            throw new IllegalStateException(
                    "La pregunta debe tener exactamente 4 opciones."
            );
        }

        // Contar las respuestas correctas
        long respuestasCorrectas = question.getOpciones()
                .stream()
                .filter(Option::isCorrecta)
                .count();

        // Validar exactamente una respuesta correcta
        if (respuestasCorrectas != 1) {

            throw new IllegalStateException(
                    "La pregunta debe tener exactamente una respuesta correcta."
            );
        }

        Question resultado = question;

        // Reiniciar el Builder para permitir otra construcción
        question = new Question();

        return resultado;
    }
}
