
package co.edu.unicauca.saberpro.state;

public class ApprovedState implements IQuestionState {

    @Override
    public void submitForReview(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta ya fue aprobada."
        );
    }

    @Override
    public void startReview(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta aprobada no puede iniciar otra revision."
        );
    }

    @Override
    public void approve(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta ya esta aprobada."
        );
    }

    @Override
    public void publish(QuestionContext question) {

        question.setState(new PublishedState());

        System.out.println(
            "Transicion: Aprobada -> Publicada"
        );
    }

    @Override
    public String getNombreEstado() {
        return "Aprobada";
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
