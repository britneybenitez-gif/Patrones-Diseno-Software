package co.edu.unicauca.saberpro.domain.model;

public class Context {

    private String texto;
    private String fuente;

    public Context(String texto, String fuente) {
        this.texto = texto;
        this.fuente = fuente;
    }

    public String getTexto() {
        return texto;
    }

    public String getFuente() {
        return fuente;
    }
}