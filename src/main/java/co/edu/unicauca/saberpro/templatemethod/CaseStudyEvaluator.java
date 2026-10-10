
package co.edu.unicauca.saberpro.templatemethod;

import java.util.Locale;

public class CaseStudyEvaluator extends QuestionEvaluator {

    private final String[] palabrasClave;

    public CaseStudyEvaluator(String... palabrasClave) {

        if (palabrasClave == null || palabrasClave.length == 0) {
            throw new IllegalArgumentException(
                "Debe proporcionar al menos una palabra clave."
            );
        }

        for (String palabra : palabrasClave) {
            if (palabra == null || palabra.isBlank()) {
                throw new IllegalArgumentException(
                    "Las palabras clave no pueden estar vacías."
                );
            }
        }

        this.palabrasClave = palabrasClave.clone();
    }

    @Override
    protected void validarFormato(String respuesta) {

        super.validarFormato(respuesta);

        if (respuesta.trim().length() < 10) {
            throw new IllegalArgumentException(
                "La respuesta del caso debe tener al menos 10 caracteres."
            );
        }
    }

    @Override
    protected double calcularPuntaje(String respuesta) {

        String respuestaNormalizada =
                respuesta.toLowerCase(Locale.ROOT);

        int coincidencias = 0;

        for (String palabra : palabrasClave) {

            if (respuestaNormalizada.contains(
                    palabra.toLowerCase(Locale.ROOT))) {
                coincidencias++;
            }
        }

        return (double) coincidencias / palabrasClave.length;
    }

    @Override
    protected String generarRetroalimentacion(
            String respuesta,
            double puntaje) {

        if (puntaje == 1.0) {
            return "Excelente. La respuesta incluye todos los conceptos esperados.";
        }

        if (puntaje >= 0.5) {
            return "Respuesta parcialmente correcta. Debe profundizar en algunos conceptos.";
        }

        return "Respuesta insuficiente. Revise los conceptos principales del caso.";
    }
}
