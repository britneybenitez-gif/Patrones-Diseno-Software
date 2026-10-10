
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;

public class QuestionBankFacadeTest {

    private QuestionBankFacade facade;

    @BeforeEach
    void configurar() {
        facade = new QuestionBankFacade();
    }

    @Test
    void debeIniciarConBancoVacio() {

        assertEquals(0, facade.contarPreguntas());

        assertTrue(facade.listarPreguntas().isEmpty());
    }

    @Test
    void debeCrearPreguntaLecturaCritica() {

        Question pregunta =
                facade.crearPreguntaLecturaCritica();

        assertNotNull(pregunta);

        assertEquals(4, pregunta.getOpciones().size());

        assertEquals(1, facade.contarPreguntas());
    }

    @Test
    void debeCrearPreguntaRazonamientoCuantitativo() {

        Question pregunta =
                facade.crearPreguntaRazonamientoCuantitativo();

        assertNotNull(pregunta);

        assertEquals(
                "Razonamiento Cuantitativo",
                pregunta.getClasificacion().getCategoria()
        );

        assertEquals(1, facade.contarPreguntas());
    }

    @Test
    void debeImportarPreguntaExterna() {

        Question pregunta =
                facade.importarPreguntaExterna();

        assertNotNull(pregunta);

        assertEquals(
                "¿Qué patrón permite adaptar interfaces incompatibles?",
                pregunta.getEnunciado()
        );

        assertEquals(4, pregunta.getOpciones().size());

        assertEquals(1, facade.contarPreguntas());
    }

    @Test
    void debeAlmacenarTresPreguntas() {

        facade.crearPreguntaLecturaCritica();

        facade.crearPreguntaRazonamientoCuantitativo();

        facade.importarPreguntaExterna();

        assertEquals(3, facade.contarPreguntas());

        List<Question> preguntas = facade.listarPreguntas();

        assertEquals(3, preguntas.size());
    }

    @Test
    void listarPreguntasNoDebeModificarBanco() {

        facade.crearPreguntaLecturaCritica();

        List<Question> preguntas = facade.listarPreguntas();

        preguntas.clear();

        assertEquals(1, facade.contarPreguntas());
    }
}
