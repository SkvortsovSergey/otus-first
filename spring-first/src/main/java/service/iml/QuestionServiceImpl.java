package service.iml;

import model.Answer;
import model.Question;
import service.CsvDataloader;
import service.QuestionService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {
    private Set<Question> questions = new HashSet<>();
    private final CsvDataloader csvDataLoader;
    public static final String QUESTIONS_CSV = "questions.csv";

    public QuestionServiceImpl(CsvDataloader csvDataLoader) {
        this.csvDataLoader = csvDataLoader;
    }

    @PostConstruct
    public void setUp() {
        questions = getQuestionSet();
    }

    @Override
    public Set<Question> getQuestions() {
        return this.questions;
    }

    private Set<Question> getQuestionSet() {
        List<String[]> questionsAndAnswers = csvDataLoader.loadManyToManyRelationship(QUESTIONS_CSV);
        Set<Question> questions = questionsAndAnswers.stream()
                .map(x -> {
                    Question question = new Question();
                    question.setQuestion(x[0]);
                    return question;
                })
                .collect(Collectors.toSet());

        for (String[] item : questionsAndAnswers) {
            Question question = findQuestion(questions, item[0]);

            Set<Answer> answers = question.getAnswers();
            if (answers == null) {
                answers = new HashSet<>();
            }
            Answer answer = new Answer();
            answer.setAnswer(item[1]);
            answers.add(answer);
            question.setAnswers(answers);
        }
        return questions;
    }

    private Question findQuestion(Set<Question> questions, String question) {
        return questions.stream()
                .filter(item -> question.equals(item.getQuestion()))
                .findFirst().orElse(null);
    }

}
