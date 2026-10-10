
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.templatemethod.QuestionEvaluator;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;
import co.edu.unicauca.saberpro.templatemethod.CaseStudyEvaluator;

public class QuestionEvaluatorTest {

    @Test
    void seleccionMultipleCorrecta() {

        QuestionEvaluator evaluador =
                new MultipleChoiceEvaluator("B");

        double puntaje = evaluador.evaluarRespuesta("B");

        assertEquals(1.0, puntaje);
        assertNotNull(evaluador.getRetroalimentacion());
    }

    @Test
    void seleccionMultipleIncorrecta() {

        QuestionEvaluator evaluador =
                new MultipleChoiceEvaluator("B");

        double puntaje = evaluador.evaluarRespuesta("A");

        assertEquals(0.0, puntaje);
    }

    @Test
    void seleccionMultipleFormatoInvalido() {

        QuestionEvaluator evaluador =
                new MultipleChoiceEvaluator("B");

        assertThrows(
                IllegalArgumentException.class,
                () -> evaluador.evaluarRespuesta("E")
        );
    }

    @Test
    void verdaderoFalsoCorrecto() {

        QuestionEvaluator evaluador =
                new TrueFalseEvaluator(true);

        double puntaje =
                evaluador.evaluarRespuesta("Verdadero");

        assertEquals(1.0, puntaje);
    }

    @Test
    void verdaderoFalsoIncorrecto() {

        QuestionEvaluator evaluador =
                new TrueFalseEvaluator(true);

        double puntaje =
                evaluador.evaluarRespuesta("Falso");

        assertEquals(0.0, puntaje);
    }

    @Test
    void verdaderoFalsoFormatoInvalido() {

        QuestionEvaluator evaluador =
                new TrueFalseEvaluator(false);

        assertThrows(
                IllegalArgumentException.class,
                () -> evaluador.evaluarRespuesta("Quizas")
        );
    }

    @Test
    void casoEstudioRespuestaCompleta() {

        QuestionEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        double puntaje = evaluador.evaluarRespuesta(
                "Se recomienda aplicar el principio abierto cerrado."
        );

        assertEquals(1.0, puntaje);
    }

    @Test
    void casoEstudioRespuestaParcial() {

        QuestionEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        double puntaje = evaluador.evaluarRespuesta(
                "El principio abierto permite extender el sistema."
        );

        assertEquals(0.5, puntaje);
    }

    @Test
    void respuestaVaciaDebeSerRechazada() {

        QuestionEvaluator evaluador =
                new MultipleChoiceEvaluator("A");

        assertThrows(
                IllegalArgumentException.class,
                () -> evaluador.evaluarRespuesta("")
        );
    }
}
