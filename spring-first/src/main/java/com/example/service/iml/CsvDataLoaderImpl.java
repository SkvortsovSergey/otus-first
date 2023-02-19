package com.example.service.iml;

import com.example.logging.LoggerAround;
import com.example.service.CsvDataLoader;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@Service
@Slf4j
public class CsvDataLoaderImpl implements CsvDataLoader {

    @Override
    @LoggerAround
    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            InputStream resourceAsStream = Objects.requireNonNull(new ClassPathResource(fileName).getClassLoader()).getResourceAsStream(fileName);
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(resourceAsStream);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
    @LoggerAround
    @Override
    public List<String[]> loadManyToManyRelationship(String fileName) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            InputStream resourceAsStream = Objects.requireNonNull(new ClassPathResource(fileName).getClassLoader()).getResourceAsStream(fileName);
            MappingIterator<String[]> readValues =
                    mapper.readerFor(String[].class).with(bootstrapSchema).readValues(resourceAsStream);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading many to many relationship from file = " + fileName, e);
            return Collections.emptyList();
        }
    }
}
