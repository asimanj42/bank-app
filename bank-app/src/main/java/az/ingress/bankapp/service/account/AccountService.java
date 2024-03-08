package az.ingress.bankapp.service.account;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.account.AccountRequest;
import az.ingress.bankapp.models.dto.account.AccountResponse;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest);
    PageResponse<AccountResponse> getAllAccount(Pageable pageable);
    AccountResponse getAccountById(Long accountId);

    AccountResponse updateAccount(AccountRequest accountRequest, Long accountId);

    AccountResponse deleteAccount(Long accountId);
}
