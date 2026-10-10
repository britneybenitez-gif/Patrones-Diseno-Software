
package co.edu.unicauca.saberpro.domain.model;

public class Justification {

    private String texto;

    public Justification(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
