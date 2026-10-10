
package co.edu.unicauca.saberpro.state;

public class PublishedState implements IQuestionState {

    @Override
    public void submitForReview(QuestionContext question) {

        throw new IllegalStateException(
            "Una pregunta publicada no puede enviarse nuevamente a revision."
        );
    }

    @Override
    public void startReview(QuestionContext question) {

        throw new IllegalStateException(
            "Una pregunta publicada no puede iniciar revision."
        );
    }

    @Override
    public void approve(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta ya fue aprobada y publicada."
        );
    }

    @Override
    public void publish(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta ya esta publicada."
        );
    }

    @Override
    public String getNombreEstado() {
        return "Publicada";
    }
    
@Override
public void requestCorrections(QuestionContext question) {

    throw new IllegalStateException(
        "Solo se pueden solicitar correcciones durante la revision."
    );
}

@Override
public void editarEnunciado(
        QuestionContext question,
        String nuevoEnunciado) {

    throw new IllegalStateException(
        "Solo se puede editar una pregunta en estado Borrador."
    );
}

}
