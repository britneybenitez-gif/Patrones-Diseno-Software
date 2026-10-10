
package co.edu.unicauca.saberpro.domain.model;

public class Option {

    private String texto;
    private boolean correcta;

    public Option(String texto, boolean correcta) {
        this.texto = texto;
        this.correcta = correcta;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    @Override
    public String toString() {
        return texto;
    }
}
