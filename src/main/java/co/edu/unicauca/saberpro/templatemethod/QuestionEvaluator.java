
package co.edu.unicauca.saberpro.templatemethod;

public abstract class QuestionEvaluator {

    private String retroalimentacion;

    // Método plantilla: define el orden del algoritmo
    public final double evaluarRespuesta(String respuestaUsuario) {

        // Paso 1: Recibir la respuesta
        String respuesta = recibirRespuesta(respuestaUsuario);

        // Paso 2: Validar su formato
        validarFormato(respuesta);

        // Paso 3: Calcular el puntaje específico
        double puntaje = calcularPuntaje(respuesta);

        // Paso 4: Generar retroalimentación
        retroalimentacion =
                generarRetroalimentacion(respuesta, puntaje);

        return puntaje;
    }

    // Paso común: recibir la respuesta
    private String recibirRespuesta(String respuestaUsuario) {
        return respuestaUsuario;
    }

    // Paso común: validar una respuesta no vacía
    protected void validarFormato(String respuesta) {

        if (respuesta == null || respuesta.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La respuesta no puede estar vacia."
            );
        }
    }

    // Paso variable: cada subclase calcula su puntaje
    protected abstract double calcularPuntaje(String respuesta);

    // Paso variable: cada subclase genera retroalimentación
    protected abstract String generarRetroalimentacion(
            String respuesta,
            double puntaje
    );

    // Consultar la retroalimentación generada
    public String getRetroalimentacion() {
        return retroalimentacion;
    }
}
