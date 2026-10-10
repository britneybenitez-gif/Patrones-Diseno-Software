
package co.edu.unicauca.saberpro.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.unicauca.saberpro.state.DraftState;
import co.edu.unicauca.saberpro.state.QuestionContext;

public class Question {

    private Context context;
    private String enunciado;
    private List<Option> opciones;
    private Justification justificacion;
    private Classification clasificacion;

    // Contexto encargado de administrar el ciclo de vida
    private QuestionContext contextoEstado;

    public Question() {
        this.opciones = new ArrayList<>();
    }

    // Vincular una sola vez el contexto de estado
    public void vincularContexto(QuestionContext contexto) {

        if (contexto == null || contexto.getQuestion() != this) {
            throw new IllegalArgumentException(
                "El contexto no corresponde a esta pregunta."
            );
        }

        if (this.contextoEstado != null) {
            throw new IllegalStateException(
                "La pregunta ya tiene un contexto de estado."
            );
        }

        this.contextoEstado = contexto;
    }

    // Comprobar que la edición esté permitida
    private void validarEdicion() {

        // Durante Builder todavía no existe un contexto
        if (contextoEstado == null) {
            return;
        }

        // Una vez vinculada, solo Borrador permite editar
        if (!(contextoEstado.getState() instanceof DraftState)) {
            throw new IllegalStateException(
                "Solo se puede modificar una pregunta en estado Borrador."
            );
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        validarEdicion();
        this.context = context;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        validarEdicion();
        this.enunciado = enunciado;
    }

    public List<Option> getOpciones() {

        // Builder necesita agregar opciones durante la construcción
        if (contextoEstado == null) {
            return opciones;
        }

        // Una vez vinculada, se impide modificar la lista directamente
        return Collections.unmodifiableList(opciones);
    }

    public void setOpciones(List<Option> opciones) {

        validarEdicion();

        if (opciones == null) {
            throw new IllegalArgumentException(
                "Las opciones no pueden ser nulas."
            );
        }

        this.opciones = new ArrayList<>(opciones);
    }

    public Justification getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(Justification justificacion) {
        validarEdicion();
        this.justificacion = justificacion;
    }

    public Classification getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Classification clasificacion) {
        validarEdicion();
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("\n========== PREGUNTA SABER PRO ==========\n");

        if (context != null) {

            sb.append("Contexto: ")
              .append(context.getTexto())
              .append("\n");

            sb.append("Fuente: ")
              .append(context.getFuente())
              .append("\n");
        }

        sb.append("\nEnunciado: ")
          .append(enunciado)
          .append("\n");

        sb.append("\nOpciones:\n");

        for (int i = 0; i < opciones.size(); i++) {

            sb.append((char) ('A' + i))
              .append(". ")
              .append(opciones.get(i).getTexto())
              .append("\n");
        }

        if (justificacion != null) {

            sb.append("\nJustificación: ")
              .append(justificacion.getTexto())
              .append("\n");
        }

        if (clasificacion != null) {

            sb.append("Clasificación: ")
              .append(clasificacion.getCategoria())
              .append("\n");
        }

        sb.append("========================================");

        return sb.toString();
    }
}
