
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionDecoratorTest {

    private QuestionComponent preguntaBasica;

    @BeforeEach
    void configurar() {

        Question pregunta = new QuestionBuilder()
                .withContext(
                        "Los patrones mejoran el diseño del software.",
                        "Ingenieria de Software"
                )
                .withEnunciado(
                        "¿Que patron permite agregar funcionalidades dinamicamente?"
                )
                .addOption("Builder", false)
                .addOption("Decorator", true)
                .addOption("State", false)
                .addOption("Adapter", false)
                .withJustification(
                        "Decorator permite agregar responsabilidades sin modificar la clase original."
                )
                .withClassification("Patrones de Diseño")
                .build();

        preguntaBasica = new BasicQuestion(pregunta);
    }

    @Test
    void debeTenerPuntajeBase() {

        assertEquals(1.0, preguntaBasica.getPuntaje());
    }

    @Test
    void debeAplicarMultiplicadorDificultad() {

        QuestionComponent preguntaDificil =
                new HighDifficultyQuestion(
                        preguntaBasica, 2.0
                );

        assertEquals(2.0, preguntaDificil.getPuntaje());
    }

    @Test
    void debeAgregarRetroalimentacion() {

        QuestionComponent pregunta =
                new FeedbackQuestion(
                        preguntaBasica,
                        "Se utiliza composicion de objetos."
                );

        assertTrue(
                pregunta.getRetroalimentacion()
                        .contains("Se utiliza composicion de objetos.")
        );
    }

    @Test
    void debeCombinarDosDecoradores() {

        QuestionComponent pregunta =
                new FeedbackQuestion(
                        new HighDifficultyQuestion(
                                preguntaBasica, 2.0
                        ),
                        "Explicacion tecnica adicional."
                );

        assertEquals(2.0, pregunta.getPuntaje());

        assertTrue(
                pregunta.getRetroalimentacion()
                        .contains("Explicacion tecnica adicional.")
        );
    }

    @Test
    void debeConservarEnunciadoOriginal() {

        QuestionComponent pregunta =
                new HighDifficultyQuestion(
                        preguntaBasica, 3.0
                );

        assertEquals(
                preguntaBasica.getEnunciado(),
                pregunta.getEnunciado()
        );
    }

    @Test
    void debeRechazarMultiplicadorInvalido() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new HighDifficultyQuestion(
                        preguntaBasica, 0.5
                )
        );
    }

    @Test
    void debeRechazarRetroalimentacionVacia() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new FeedbackQuestion(
                        preguntaBasica, ""
                )
        );
    }
}
