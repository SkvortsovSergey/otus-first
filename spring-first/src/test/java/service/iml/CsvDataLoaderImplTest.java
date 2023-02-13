package service.iml;

import com.example.model.Answer;
import com.example.service.CsvDataLoader;
import com.example.service.iml.CsvDataLoaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CsvDataLoaderImplTest {
    private CsvDataLoader csvDataLoader;

    @BeforeEach
    void setUp() {
    csvDataLoader = new CsvDataLoaderImpl();
    }

    @Test
    void loadObjectListTest() {
        List<Answer> answers = csvDataLoader.loadObjectList(Answer.class, "answer-test.csv");
        assertFalse(answers.isEmpty());
        assertEquals("a",answers.get(0).getAnswer());
    }

    @Test
    void loadManyToManyRelationshipTest() {
        List<String[]> strings = csvDataLoader.loadManyToManyRelationship("question-test.csv");
        assertFalse(strings.isEmpty());
        assertEquals("a",strings.get(0)[1]);

    }
}