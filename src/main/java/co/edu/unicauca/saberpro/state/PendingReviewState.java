
package co.edu.unicauca.saberpro.state;

public class PendingReviewState implements IQuestionState {

    @Override
    public void submitForReview(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta ya fue enviada a revision."
        );
    }

    @Override
    public void startReview(QuestionContext question) {

        question.setState(new ReviewState());

        System.out.println(
            "Transicion: Pendiente de Revision -> En Revision"
        );
    }

    @Override
    public void approve(QuestionContext question) {

        throw new IllegalStateException(
            "No se puede aprobar sin iniciar la revision."
        );
    }

    @Override
    public void publish(QuestionContext question) {

        throw new IllegalStateException(
            "No se puede publicar una pregunta pendiente de revision."
        );
    }

    @Override
    public String getNombreEstado() {
        return "Pendiente de Revision";
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
