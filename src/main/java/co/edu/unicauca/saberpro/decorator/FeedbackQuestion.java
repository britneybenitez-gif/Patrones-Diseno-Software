
package co.edu.unicauca.saberpro.decorator;

public class FeedbackQuestion extends QuestionDecorator {

    private final String explicacionAdicional;

    public FeedbackQuestion(
            QuestionComponent component,
            String explicacionAdicional) {

        super(component);

        if (explicacionAdicional == null ||
                explicacionAdicional.isBlank()) {

            throw new IllegalArgumentException(
                    "La explicacion adicional no puede estar vacia."
            );
        }

        this.explicacionAdicional = explicacionAdicional;
    }

    @Override
    public String getRetroalimentacion() {

        return component.getRetroalimentacion()
                + "\nExplicacion tecnica: "
                + explicacionAdicional;
    }
}
