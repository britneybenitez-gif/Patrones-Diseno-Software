
package co.edu.unicauca.saberpro.service;

import java.util.List;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.repository.QuestionRepository;
import co.edu.unicauca.saberpro.validation.QuestionValidator;

public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionValidator validator;

    public QuestionService(
            QuestionRepository repository,
            QuestionValidator validator) {

        this.repository = repository;
        this.validator = validator;
    }

    public Question registrar(Question pregunta) {

        validator.validar(pregunta);
        repository.guardar(pregunta);

        return pregunta;
    }

    public List<Question> listar() {
        return repository.listar();
    }

    public int contar() {
        return repository.contar();
    }
}
