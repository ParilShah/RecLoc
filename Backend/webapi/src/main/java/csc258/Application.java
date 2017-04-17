package csc258;

import csc258.bootstrap.Bootstrap;
import csc258.spring.ApplicationContextProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            Bootstrap bootstrap = ApplicationContextProvider.context.getBean(Bootstrap.class);
            bootstrap.init();
        };
    }
}