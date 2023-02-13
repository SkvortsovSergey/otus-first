package com.example.service;

import com.example.model.Question;

import java.util.Set;

public interface QuestionService {
    /**
     * Метод для получения списка вопросов с ответами из файла
     *
     * @return множество воросов
     */
    Set<Question> getQuestions();
}
