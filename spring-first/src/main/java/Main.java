import configuration.CsvConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import service.QuestionService;

@ComponentScan(basePackageClasses = CsvConfig.class)
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService bean = context.getBean(QuestionService.class);

        bean.getQuestions().forEach(System.out::println);
    }
}
