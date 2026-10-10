
package co.edu.unicauca.saberpro.state;

public class ReviewState implements IQuestionState {

    @Override
    public void submitForReview(QuestionContext question) {
        throw new IllegalStateException(
            "La pregunta ya está en revisión."
        );
    }

    @Override
    public void startReview(QuestionContext question) {
        throw new IllegalStateException(
            "La revisión ya comenzó."
        );
    }

    @Override
    public void approve(QuestionContext question) {

        question.setState(new ApprovedState());

        System.out.println(
            "Transición: En Revisión -> Aprobada"
        );
    }

    @Override
    public void requestCorrections(QuestionContext question) {

        question.setState(new DraftState());

        System.out.println(
            "Transición: En Revisión -> Borrador (Correcciones)"
        );
    }

    @Override
    public void publish(QuestionContext question) {
        throw new IllegalStateException(
            "La pregunta debe aprobarse antes de publicarse."
        );
    }

    @Override
    public String getNombreEstado() {
        return "En Revisión";
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
