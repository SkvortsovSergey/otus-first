package com.example.service;

import java.util.List;

public interface CsvDataLoader {
    /**
     * Метод преобразует CSV файл в список объектов
     *
     * @param type     тип объекта в который надо преобразовать файл
     * @param fileName имя файла, который надо преобразовать
     * @param <T>      тип объекта
     * @return список объектов
     */
    <T> List<T> loadObjectList(Class<T> type, String fileName);

    /**
     * Метод преобразует CSV файл в список массивов строк
     *
     * @param fileName имя файла, который надо преобразовать
     * @return список массивов строк
     */
    List<String[]> loadManyToManyRelationship(String fileName);
}
