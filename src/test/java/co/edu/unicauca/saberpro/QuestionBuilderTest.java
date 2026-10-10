
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionBuilderTest {

    @Test
    void debeConstruirPreguntaValida() {

        Question pregunta = new QuestionBuilder()
                .withContext("Contexto de prueba", "Fuente")
                .withEnunciado("¿Qué patrón es creacional?")
                .addOption("Builder", true)
                .addOption("Adapter", false)
                .addOption("Facade", false)
                .addOption("State", false)
                .withJustification("Builder es creacional.")
                .withClassification("Ingeniería de Software")
                .build();

        assertNotNull(pregunta);
        assertEquals(4, pregunta.getOpciones().size());
        assertEquals("¿Qué patrón es creacional?",
                pregunta.getEnunciado());
    }

    @Test
    void debeRechazarMenosDeCuatroOpciones() {

        QuestionBuilder builder = new QuestionBuilder();

        builder.withEnunciado("Pregunta incompleta")
                .addOption("A", true)
                .addOption("B", false)
                .addOption("C", false);

        assertThrows(
                IllegalStateException.class,
                () -> builder.build()
        );
    }

    @Test
    void debeRechazarDosRespuestasCorrectas() {

        QuestionBuilder builder = new QuestionBuilder();

        builder.withEnunciado("Pregunta inválida")
                .addOption("A", true)
                .addOption("B", true)
                .addOption("C", false)
                .addOption("D", false);

        assertThrows(
                IllegalStateException.class,
                () -> builder.build()
        );
    }

    @Test
    void debeRechazarPreguntaSinRespuestaCorrecta() {

        QuestionBuilder builder = new QuestionBuilder();

        builder.withEnunciado("Pregunta inválida")
                .addOption("A", false)
                .addOption("B", false)
                .addOption("C", false)
                .addOption("D", false);

        assertThrows(
                IllegalStateException.class,
                () -> builder.build()
        );
    }
}
