
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.templatemethod.CaseStudyEvaluator;

public class CaseStudyEvaluatorTest {

    @Test
    void debeEvaluarRespuestaCompleta() {

        CaseStudyEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        double puntaje = evaluador.evaluarRespuesta(
                "Se debe aplicar el principio abierto cerrado."
        );

        assertEquals(1.0, puntaje);

        assertNotNull(evaluador.getRetroalimentacion());
    }

    @Test
    void debeEvaluarRespuestaParcial() {

        CaseStudyEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        double puntaje = evaluador.evaluarRespuesta(
                "Se debe aplicar el principio abierto."
        );

        assertEquals(0.5, puntaje);
    }

    @Test
    void debeRechazarRespuestaVacia() {

        CaseStudyEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        assertThrows(
                IllegalArgumentException.class,
                () -> evaluador.evaluarRespuesta("")
        );
    }

    @Test
    void debeRechazarRespuestaMuyCorta() {

        CaseStudyEvaluator evaluador =
                new CaseStudyEvaluator("abierto", "cerrado");

        assertThrows(
                IllegalArgumentException.class,
                () -> evaluador.evaluarRespuesta("Abierto")
        );
    }
}
