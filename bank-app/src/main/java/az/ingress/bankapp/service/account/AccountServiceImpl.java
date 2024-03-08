package az.ingress.bankapp.service.account;

import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.account.AccountRequest;
import az.ingress.bankapp.models.dto.account.AccountResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.mapper.account.AccountMapper;
import az.ingress.bankapp.mapper.pagination.PageResponseMapper;
import az.ingress.bankapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PageResponseMapper pageResponseMapper;

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = accountMapper.accountRequestToEntity(accountRequest);
        Account savedAccount = accountRepository.save(account);
        return getAccountResponse(savedAccount);
    }

    @Override
    public PageResponse<AccountResponse> getAllAccount(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        Page<AccountResponse> accountResponses = mapAccountEntityToAccountResponse(accounts);
        return getCustomAccountResponsePage(accountResponses);
    }

    @Override
    public AccountResponse getAccountById(Long accountId) {
        Account account = checkAccountExistingGivenId(accountId);
        return getAccountResponse(account);
    }

    @Override
    public AccountResponse updateAccount(AccountRequest accountRequest, Long accountId) {
        Account account = checkAccountExistingGivenId(accountId);
        Account updatedAccount = updateIfNotNull(accountRequest, account);
        updatedAccount.setId(accountId);
        Account savedAccount = accountRepository.save(updatedAccount);
        return getAccountResponse(savedAccount);
    }

    @Override
    public AccountResponse deleteAccount(Long accountId) {
        Account account = checkAccountExistingGivenId(accountId);
        accountRepository.delete(account);
        return getAccountResponse(account);
    }

    private Page<AccountResponse> mapAccountEntityToAccountResponse(Page<Account> accounts) {
        return accounts.map(accountMapper::accountEntityToResponse);
    }

    private PageResponse<AccountResponse> getCustomAccountResponsePage(Page<AccountResponse> accountResponses) {
        return pageResponseMapper.mapPageResponse(accountResponses);
    }

    private AccountResponse getAccountResponse(Account savedAccount) {
        return accountMapper.accountEntityToResponse(savedAccount);
    }
    private Account checkAccountExistingGivenId(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> BaseException.of(ACCOUNT_NOT_FOUND)
        );
    }

    private Account updateIfNotNull(AccountRequest accountRequest, Account account) {
        if (accountRequest.getAccountNumber() != null) {
            account.setAccountNumber(accountRequest.getAccountNumber());
        }
        if (accountRequest.getBalance() != null) {
            account.setBalance(accountRequest.getBalance());
        }
        return account;
    }


}
