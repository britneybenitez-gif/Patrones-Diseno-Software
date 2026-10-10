
package co.edu.unicauca.saberpro.templatemethod;

public class MultipleChoiceEvaluator extends QuestionEvaluator {

    private final String respuestaCorrecta;

    public MultipleChoiceEvaluator(String respuestaCorrecta) {

        if (respuestaCorrecta == null ||
                !respuestaCorrecta.trim().matches("(?i)[A-D]")) {

            throw new IllegalArgumentException(
                    "La respuesta correcta debe estar entre A y D."
            );
        }

        this.respuestaCorrecta =
                respuestaCorrecta.trim().toUpperCase();
    }

    @Override
    protected void validarFormato(String respuesta) {

        // Reutilizar la validación general
        super.validarFormato(respuesta);

        // Validar que sea A, B, C o D
        if (!respuesta.trim().matches("(?i)[A-D]")) {

            throw new IllegalArgumentException(
                    "Debe seleccionar una opcion entre A y D."
            );
        }
    }

    @Override
    protected double calcularPuntaje(String respuesta) {

        if (respuesta.trim().equalsIgnoreCase(respuestaCorrecta)) {
            return 1.0;
        }

        return 0.0;
    }

    @Override
    protected String generarRetroalimentacion(
            String respuesta,
            double puntaje) {

        if (puntaje == 1.0) {

            return "Respuesta correcta. Obtuvo 1 punto.";
        }

        return "Respuesta incorrecta. La opcion correcta era "
                + respuestaCorrecta + ".";
    }
}
