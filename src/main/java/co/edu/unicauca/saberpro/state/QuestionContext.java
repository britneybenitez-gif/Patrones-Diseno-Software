
package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionContext {

    private final Question question;
    private IQuestionState state;

    // Constructor
    public QuestionContext(Question question) {

        if (question == null) {
            throw new IllegalArgumentException(
                "La pregunta no puede ser nula."
            );
        }

        this.question = question;

        // Toda pregunta inicia en Borrador
        this.state = new DraftState();

        // Vincular la pregunta con su ciclo de vida
        this.question.vincularContexto(this);
    }

    // Consultar la pregunta
    public Question getQuestion() {
        return question;
    }

    // Consultar el estado actual
    public IQuestionState getState() {
        return state;
    }

    // Cambiar el estado
    // Solo las clases del paquete state pueden utilizarlo
    void setState(IQuestionState nuevoEstado) {

        if (nuevoEstado == null) {
            throw new IllegalArgumentException(
                "El estado no puede ser nulo."
            );
        }

        this.state = nuevoEstado;
    }

    // Consultar el nombre del estado
    public String getNombreEstado() {
        return state.getNombreEstado();
    }

    // Enviar a revisión
    public void submitForReview() {
        state.submitForReview(this);
    }

    // Iniciar revisión
    public void startReview() {
        state.startReview(this);
    }

    // Aprobar pregunta
    public void approve() {
        state.approve(this);
    }

    // Solicitar correcciones
    public void requestCorrections() {
        state.requestCorrections(this);
    }

    // Publicar pregunta
    public void publish() {
        state.publish(this);
    }

    // Editar el enunciado
    public void editarEnunciado(String nuevoEnunciado) {
        state.editarEnunciado(this, nuevoEnunciado);
    }
}
