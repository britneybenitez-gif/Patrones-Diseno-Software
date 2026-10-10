
package co.edu.unicauca.saberpro.state;

public class DraftState implements IQuestionState {

    @Override
    public void submitForReview(QuestionContext question) {

        question.setState(new PendingReviewState());

        System.out.println(
            "Transicion: Borrador -> Pendiente de Revision"
        );
    }

    @Override
    public void startReview(QuestionContext question) {

        throw new IllegalStateException(
            "La pregunta debe enviarse antes de iniciar su revision."
        );
    }

    @Override
    public void approve(QuestionContext question) {

        throw new IllegalStateException(
            "Una pregunta en borrador debe revisarse antes de aprobar."
        );
    }

    @Override
    public void publish(QuestionContext question) {

        throw new IllegalStateException(
            "No se puede publicar una pregunta en estado borrador."
        );
    }

    @Override
    public String getNombreEstado() {
        return "Borrador";
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

    if (nuevoEnunciado == null || nuevoEnunciado.isBlank()) {
        throw new IllegalArgumentException(
            "El enunciado no puede estar vacio."
        );
    }

    question.getQuestion().setEnunciado(nuevoEnunciado);

    System.out.println(
        "Enunciado actualizado correctamente."
    );
}

}
