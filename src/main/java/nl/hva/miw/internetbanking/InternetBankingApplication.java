package nl.hva.miw.internetbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"nl.hva.miw.internetbanking"})
public class InternetBankingApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(InternetBankingApplication.class, args);
    }

}
