
package co.edu.unicauca.saberpro.decorator;

public class HighDifficultyQuestion extends QuestionDecorator {

    private final double multiplicador;

    public HighDifficultyQuestion(
            QuestionComponent component,
            double multiplicador) {

        super(component);

        if (multiplicador <= 1.0 ||
                Double.isNaN(multiplicador) ||
                Double.isInfinite(multiplicador)) {

            throw new IllegalArgumentException(
                    "El multiplicador debe ser mayor que 1."
            );
        }

        this.multiplicador = multiplicador;
    }

    @Override
    public double getPuntaje() {

        return component.getPuntaje() * multiplicador;
    }

    @Override
    public String getRetroalimentacion() {

        return component.getRetroalimentacion()
                + "\nPregunta de alta dificultad.";
    }
}
