package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.example.service.CsvDataloader;
import com.example.service.QuestionService;
@ComponentScan(basePackages = "com.example.*")
@Configuration
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService bean = context.getBean(QuestionService.class);

        bean.getQuestions().forEach(System.out::println);
    }
}
