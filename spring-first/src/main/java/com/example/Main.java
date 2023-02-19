package com.example;

import com.example.logging.LoggerAround;
import com.example.model.Question;
import com.example.service.QuestionService;
import com.example.service.StudentCheckService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Scanner;
import java.util.Set;
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example.*")
@Configuration
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        StudentCheckService checkService = context.getBean(StudentCheckService.class);
        Set<Question> questions = questionService.getQuestions();
        if (!questions.isEmpty()){
            System.out.println("Hello, enter your answer:");
            Scanner scanner = new Scanner(System.in);
            for (var item: questions){
                System.out.println(item.getQuestion());
                String answer = scanner.nextLine();
                boolean result = checkService.checkStudent(item, answer);
                if (!result){
                    System.out.println("Test provalen");
                    return;
                }
            }
            System.out.println("Test proiden!!!");

        }


    }
}
