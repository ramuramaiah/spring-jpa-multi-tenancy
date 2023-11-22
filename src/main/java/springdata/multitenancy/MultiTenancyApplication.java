package springdata.multitenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("springdata.*")
public class MultiTenancyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTenancyApplication.class, args);
    }

}
