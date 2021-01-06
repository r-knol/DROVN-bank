package nl.hva.miw.internetbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class InternetBankingApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(InternetBankingApplication.class, args);
    }
}
