package az.ingress.bankapp.service.transaction;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.transaction.TransactionRequest;
import az.ingress.bankapp.models.dto.transaction.TransactionResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.entity.Transaction;
import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.mapper.pagination.PageResponseMapper;
import az.ingress.bankapp.mapper.transaction.TransactionMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.*;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final PageResponseMapper pageResponseMapper;


    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.transactionRequestToEntity(transactionRequest);

        setAccountsToTransaction(transactionRequest, transaction);
        setAccountsAmount(transactionRequest);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return getTransactionResponse(savedTransaction);
    }


    @Override
    public PageResponse<TransactionResponse> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        Page<TransactionResponse> transactionPageResponse = mapPageEntityToPageResponse(transactions);
        return getTransactionPageResponse(transactionPageResponse);
    }

    @Override
    public TransactionResponse getTransactionById(Long transactionId) {
        Transaction transaction = checkTransactionExistingGivenId(transactionId);
        return getTransactionResponse(transaction);
    }

    private void setAccountsToTransaction(TransactionRequest transactionRequest, Transaction transaction) {
        transaction.setToAccount(checkToAccountExisting(transactionRequest));
        transaction.setFromAccount(checkFromAccountExisting(transactionRequest));
    }

    private void setAccountsAmount(TransactionRequest transactionRequest) {
        updateFromAccountBalance(transactionRequest);
        updateToAccountBalance(transactionRequest);
    }

    private TransactionResponse getTransactionResponse(Transaction savedTransaction) {
        return transactionMapper.transactionEntityToResponse(savedTransaction);
    }

    private Page<TransactionResponse> mapPageEntityToPageResponse(Page<Transaction> page) {
        return page.map(transactionMapper::transactionEntityToResponse);
    }

    private PageResponse<TransactionResponse> getTransactionPageResponse(Page<TransactionResponse> transactions) {
        return pageResponseMapper.mapPageResponse(transactions);
    }

    private Transaction checkTransactionExistingGivenId(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(
                () -> BaseException.of(TRANSACTION_NOT_FOUND)
        );
    }

    private void isBalanceEnough(TransactionRequest transactionRequest) {
        Account fromAccount = checkFromAccountExisting(transactionRequest);
        if (fromAccount.getBalance() < transactionRequest.getAmount()) {
            throw BaseException.of(INSUFFICIENT_BALANCE);
        }
    }

    private Account checkFromAccountExisting(TransactionRequest transactionRequest) {
        return accountRepository.findById(transactionRequest.getFromAccountId())
                .orElseThrow(() ->
                        BaseException.of(FROM_ACCOUNT_NOT_FOUND)
                );
    }

    private Account checkToAccountExisting(TransactionRequest transactionRequest) {
        return accountRepository.findById(transactionRequest.getToAccountId()).orElseThrow(
                () -> BaseException.of(TO_ACCOUNT_NOT_FOUND)
        );
    }

    private void updateToAccountBalance(TransactionRequest transactionRequest) {
        Account toAccount = checkToAccountExisting(transactionRequest);
        toAccount.setBalance(toAccount.getBalance() + transactionRequest.getAmount());
        accountRepository.save(toAccount);
    }

    private void updateFromAccountBalance(TransactionRequest transactionRequest) {
        Account fromAccount = checkFromAccountExisting(transactionRequest);
        isBalanceEnough(transactionRequest);
        fromAccount.setBalance(fromAccount.getBalance() - transactionRequest.getAmount());
        accountRepository.save(fromAccount);
    }


}
