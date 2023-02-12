package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CsvDataloader;
import service.QuestionService;
import service.iml.CsvDataloaderImpl;
import service.iml.QuestionServiceImpl;

@Configuration
public class CsvConfig {
    @Bean
    public CsvDataloader csvDataloader() {
        return new CsvDataloaderImpl();
    }

    @Bean
    public QuestionService questionService(CsvDataloader csvDataloader) {
        return new QuestionServiceImpl(csvDataloader);
    }
}
