package team.cfc.lostandfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableCaching
public class Applocation {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Applocation.class, args);
    }
}
