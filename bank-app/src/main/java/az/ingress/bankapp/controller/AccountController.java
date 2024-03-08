package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.account.AccountRequest;
import az.ingress.bankapp.models.dto.account.AccountResponse;
import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AccountResponse> getAllAccount(Pageable pageable) {
        return accountService.getAllAccount(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @PutMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse updateAccount(@RequestBody AccountRequest accountRequest, @PathVariable("accountId") Long accountId) {
        return accountService.updateAccount(accountRequest, accountId);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse deleteAccount(@PathVariable("accountId") Long accountId) {
        return accountService.deleteAccount(accountId);
    }

}
