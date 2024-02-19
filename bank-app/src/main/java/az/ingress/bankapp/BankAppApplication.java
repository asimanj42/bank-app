package az.ingress.bankapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BankAppApplication  {

//    private final EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Card card = entityManager
//                .createQuery("select c from Card c where c.id=:id", Card.class)
//                .setParameter("id", 3L).getSingleResult();
//        card.setCardName("miles card");
//        entityManager.persist(card);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//
//    }
}
