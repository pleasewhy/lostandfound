package team.cfc.lostandfound;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Applocation {
    public static void main(String[] args) {
        SpringApplication.run(Applocation.class, args);
    }
}
