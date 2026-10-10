
package co.edu.unicauca.saberpro.state;

public interface IQuestionState {

    void submitForReview(QuestionContext question);

    void startReview(QuestionContext question);

    void approve(QuestionContext question);

    void requestCorrections(QuestionContext question);

    void publish(QuestionContext question);

    void editarEnunciado(QuestionContext question, String nuevoEnunciado);

    String getNombreEstado();
}
