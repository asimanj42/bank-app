package az.ingress.bankapp.repository;

import az.ingress.bankapp.models.dto.AccountDto;
import az.ingress.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

//
//    @Query("select a from Account a join fetch a.user u join fetch a.cards c join fetch c.benefits b")
//    List<Account> findByCustom();
//
//    @Query("select new az.ingress.bankapp.dto.AccountDto(a.id, a.accountNumber, a.balance, u.username, u.password, c.cardNumber, c.cardType, c.expirationDate) from Account a  join a.user u join a.cards c join c.benefits b")
//    List<AccountDto> findAllCustom();


}
