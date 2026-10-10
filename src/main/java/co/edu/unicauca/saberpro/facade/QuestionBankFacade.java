
package co.edu.unicauca.saberpro.facade;

import java.util.List;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.adapter.QuestionProvider;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.repository.QuestionRepository;
import co.edu.unicauca.saberpro.service.QuestionService;
import co.edu.unicauca.saberpro.validation.QuestionValidator;

public class QuestionBankFacade {

    private final QuestionDirector director;
    private final QuestionProvider proveedorExterno;
    private final QuestionService questionService;

    // Constructor
    public QuestionBankFacade() {

        this.director = new QuestionDirector();

        // Configurar el adaptador externo
        ExternalQuestionService servicioExterno =
                new ExternalQuestionService();

        this.proveedorExterno =
                new ExternalQuestionAdapter(servicioExterno);

        // Configurar los subsistemas internos
        QuestionRepository repository =
                new QuestionRepository();

        QuestionValidator validator =
                new QuestionValidator();

        this.questionService =
                new QuestionService(repository, validator);
    }

    // ==========================================
    // CREAR PREGUNTA DE LECTURA CRITICA
    // ==========================================

    public Question crearPreguntaLecturaCritica() {

        IQuestionBuilder builder = new QuestionBuilder();

        Question pregunta =
                director.buildPreguntaLecturaCritica(builder);

        return questionService.registrar(pregunta);
    }

    // ==========================================
    // CREAR PREGUNTA CUANTITATIVA
    // ==========================================

    public Question crearPreguntaRazonamientoCuantitativo() {

        IQuestionBuilder builder = new QuestionBuilder();

        Question pregunta =
                director.buildPreguntaRazonamientoCuantitativo(builder);

        return questionService.registrar(pregunta);
    }

    // ==========================================
    // IMPORTAR PREGUNTA EXTERNA
    // ==========================================

    public Question importarPreguntaExterna() {

        Question pregunta = proveedorExterno.getQuestion();

        return questionService.registrar(pregunta);
    }

    // ==========================================
    // LISTAR PREGUNTAS
    // ==========================================

    public List<Question> listarPreguntas() {

        return questionService.listar();
    }

    // ==========================================
    // CONTAR PREGUNTAS
    // ==========================================

    public int contarPreguntas() {

        return questionService.contar();
    }
}
