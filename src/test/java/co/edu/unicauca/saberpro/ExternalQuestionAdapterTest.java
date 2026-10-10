
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.adapter.QuestionProvider;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

public class ExternalQuestionAdapterTest {

    @Test
    void debeImportarPreguntaExterna() {

        ExternalQuestionService servicio =
                new ExternalQuestionService();

        QuestionProvider adaptador =
                new ExternalQuestionAdapter(servicio);

        Question pregunta = adaptador.getQuestion();

        assertNotNull(pregunta);

        assertEquals(4, pregunta.getOpciones().size());

        assertEquals(
                "¿Qué patrón permite adaptar interfaces incompatibles?",
                pregunta.getEnunciado()
        );
    }

    @Test
    void debeTenerUnaSolaRespuestaCorrecta() {

        QuestionProvider adaptador =
                new ExternalQuestionAdapter(
                        new ExternalQuestionService()
                );

        Question pregunta = adaptador.getQuestion();

        long correctas = pregunta.getOpciones()
                .stream()
                .filter(Option::isCorrecta)
                .count();

        assertEquals(1, correctas);
    }

    @Test
    void debeRechazarFormatoExternoInvalido() {

        ExternalQuestionService servicioInvalido =
                new ExternalQuestionService() {

                    @Override
                    public String getQuestionData() {
                        return "Datos incompletos";
                    }
                };

        QuestionProvider adaptador =
                new ExternalQuestionAdapter(servicioInvalido);

        assertThrows(
                IllegalArgumentException.class,
                () -> adaptador.getQuestion()
        );
    }
}
