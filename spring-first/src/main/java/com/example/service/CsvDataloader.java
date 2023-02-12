package com.example.service;

import java.util.List;

public interface CsvDataloader {
    <T> List<T> loadObjectList(Class<T> type, String fileName);

    List<String[]> loadManyToManyRelationship(String fileName);
}
