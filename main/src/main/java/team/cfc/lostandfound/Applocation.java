package team.cfc.lostandfound;


import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import team.cfc.lostandfound.model.WxUser;
import team.cfc.lostandfound.service.WxUserService;

@SpringBootApplication
@EnableCaching
public class Applocation {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Applocation.class, args);
    }
}
