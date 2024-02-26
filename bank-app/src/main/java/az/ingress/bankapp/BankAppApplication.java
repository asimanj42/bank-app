package az.ingress.bankapp;

import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BankAppApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        accountRepository.findByCustom().forEach(System.out::println);

        accountRepository.findByAccountNumber("iba123").forEach(System.out::println);


    }
}
