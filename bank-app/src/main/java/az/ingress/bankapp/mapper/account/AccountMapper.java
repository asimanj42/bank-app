package az.ingress.bankapp.mapper.account;

import az.ingress.bankapp.models.dto.account.AccountRequest;
import az.ingress.bankapp.models.dto.account.AccountResponse;
import az.ingress.bankapp.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account accountRequestToEntity(AccountRequest accountRequest) {

        return Account.builder()
                .accountNumber(accountRequest.getAccountNumber())
                .balance(accountRequest.getBalance())
                .build();
    }

    public AccountResponse accountEntityToResponse(Account account) {

        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .build();
    }
}
