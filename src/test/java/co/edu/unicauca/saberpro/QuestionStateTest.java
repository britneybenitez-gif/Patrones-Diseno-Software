
package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.state.QuestionContext;

public class QuestionStateTest {

    private QuestionContext contexto;

    @BeforeEach
    void configurar() {

        Question pregunta = new QuestionBuilder()
                .withContext("Texto de prueba", "Fuente academica")
                .withEnunciado("¿Que tipo de patrón es State?")
                .addOption("Creacional", false)
                .addOption("Estructural", false)
                .addOption("De comportamiento", true)
                .addOption("Arquitectonico", false)
                .withJustification(
                        "State pertenece a los patrones de comportamiento."
                )
                .withClassification("Ingenieria de Software")
                .build();

        contexto = new QuestionContext(pregunta);
    }

    @Test
    void debeIniciarEnBorrador() {

        assertEquals(
                "Borrador",
                contexto.getNombreEstado()
        );
    }

    @Test
    void debePasarAPendienteRevision() {

        contexto.submitForReview();

        assertEquals(
                "Pendiente de Revision",
                contexto.getNombreEstado()
        );
    }

    @Test
    void debeIniciarRevision() {

        contexto.submitForReview();
        contexto.startReview();

        assertEquals(
                "En Revision",
                contexto.getNombreEstado()
        );
    }

    @Test
    void debeAprobarPreguntaRevisada() {

        contexto.submitForReview();
        contexto.startReview();
        contexto.approve();

        assertEquals(
                "Aprobada",
                contexto.getNombreEstado()
        );
    }

    @Test
    void debePublicarPreguntaAprobada() {

        contexto.submitForReview();
        contexto.startReview();
        contexto.approve();
        contexto.publish();

        assertEquals(
                "Publicada",
                contexto.getNombreEstado()
        );
    }

    @Test
    void noDebeAprobarDesdeBorrador() {

        assertThrows(
                IllegalStateException.class,
                () -> contexto.approve()
        );

        assertEquals(
                "Borrador",
                contexto.getNombreEstado()
        );
    }

    @Test
    void noDebePublicarDesdeBorrador() {

        assertThrows(
                IllegalStateException.class,
                () -> contexto.publish()
        );

        assertEquals(
                "Borrador",
                contexto.getNombreEstado()
        );
    }

    @Test
    void noDebePublicarDosVeces() {

        contexto.submitForReview();
        contexto.startReview();
        contexto.approve();
        contexto.publish();

        assertThrows(
                IllegalStateException.class,
                () -> contexto.publish()
        );

        assertEquals(
                "Publicada",
                contexto.getNombreEstado()
        );
    }
    
@Test
void debePermitirSolicitarCorrecciones() {

    contexto.submitForReview();
    contexto.startReview();

    contexto.requestCorrections();

    assertEquals(
        "Borrador",
        contexto.getNombreEstado()
    );
}

@Test
void noDebeSolicitarCorreccionesDesdeBorrador() {

    assertThrows(
        IllegalStateException.class,
        () -> contexto.requestCorrections()
    );
}

@Test
void debePermitirEditarEnBorrador() {

    contexto.editarEnunciado(
        "Nuevo enunciado corregido"
    );

    assertEquals(
        "Nuevo enunciado corregido",
        contexto.getQuestion().getEnunciado()
    );
}

@Test
void noDebePermitirEditarEnRevision() {

    contexto.submitForReview();
    contexto.startReview();

    assertThrows(
        IllegalStateException.class,
        () -> contexto.editarEnunciado("Cambio no permitido")
    );
}

@Test
void noDebeModificarEnunciadoDirectamenteEnRevision() {

    contexto.submitForReview();
    contexto.startReview();

    assertThrows(
        IllegalStateException.class,
        () -> contexto.getQuestion()
                     .setEnunciado("Modificacion ilegal")
    );
}

@Test
void noDebeModificarOpcionesDirectamenteEnRevision() {

    contexto.submitForReview();

    assertThrows(
        UnsupportedOperationException.class,
        () -> contexto.getQuestion()
                     .getOpciones()
                     .clear()
    );
}

@Test
void debePermitirModificarEnunciadoEnBorrador() {

    contexto.editarEnunciado("Enunciado actualizado");

    assertEquals(
        "Enunciado actualizado",
        contexto.getQuestion().getEnunciado()
    );
}

}
