
package co.edu.unicauca.saberpro.decorator;

public abstract class QuestionDecorator implements QuestionComponent {

    protected final QuestionComponent component;

    public QuestionDecorator(QuestionComponent component) {

        if (component == null) {
            throw new IllegalArgumentException(
                    "El componente no puede ser nulo."
            );
        }

        this.component = component;
    }

    @Override
    public String getEnunciado() {
        return component.getEnunciado();
    }

    @Override
    public double getPuntaje() {
        return component.getPuntaje();
    }

    @Override
    public String getRetroalimentacion() {
        return component.getRetroalimentacion();
    }
}
