
package co.edu.unicauca.saberpro.templatemethod;

public class TrueFalseEvaluator extends QuestionEvaluator {

    private final boolean respuestaCorrecta;

    // Constructor
    public TrueFalseEvaluator(boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    // Validar que la respuesta sea Verdadero o Falso
    @Override
    protected void validarFormato(String respuesta) {

        super.validarFormato(respuesta);

        String valor = respuesta.trim();

        if (!valor.equalsIgnoreCase("Verdadero")
                && !valor.equalsIgnoreCase("Falso")) {

            throw new IllegalArgumentException(
                    "La respuesta debe ser Verdadero o Falso."
            );
        }
    }

    // Calcular puntaje
    @Override
    protected double calcularPuntaje(String respuesta) {

        boolean respuestaUsuario =
                respuesta.trim().equalsIgnoreCase("Verdadero");

        if (respuestaUsuario == respuestaCorrecta) {
            return 1.0;
        }

        return 0.0;
    }

    // Generar retroalimentacion
    @Override
    protected String generarRetroalimentacion(
            String respuesta,
            double puntaje) {

        if (puntaje == 1.0) {
            return "Respuesta correcta. Obtuvo 1 punto.";
        }

        return "Respuesta incorrecta. La respuesta correcta era "
                + (respuestaCorrecta ? "Verdadero" : "Falso")
                + ".";
    }
}
