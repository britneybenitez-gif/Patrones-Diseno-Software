
package co.edu.unicauca.saberpro.domain.model;

public class Classification {

    private String categoria;

    public Classification(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
