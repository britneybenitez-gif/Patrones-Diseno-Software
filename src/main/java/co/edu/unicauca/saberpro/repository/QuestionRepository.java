
package co.edu.unicauca.saberpro.repository;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.saberpro.domain.model.Question;

public class QuestionRepository {

    private final List<Question> preguntas;

    public QuestionRepository() {
        this.preguntas = new ArrayList<>();
    }

    public void guardar(Question pregunta) {

        if (pregunta == null) {
            throw new IllegalArgumentException(
                "No se puede guardar una pregunta nula."
            );
        }

        preguntas.add(pregunta);
    }

    public List<Question> listar() {
        return new ArrayList<>(preguntas);
    }

    public int contar() {
        return preguntas.size();
    }
}
