package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.transaction.TransactionRequest;
import az.ingress.bankapp.models.dto.transaction.TransactionResponse;
import az.ingress.bankapp.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

private final TransactionService transactionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<TransactionResponse> getTransactions(Pageable pageable) {
        return transactionService.getAllTransactions(pageable);
    }

    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransactionById(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

}
