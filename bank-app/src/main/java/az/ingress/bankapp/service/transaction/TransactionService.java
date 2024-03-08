package az.ingress.bankapp.service.transaction;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.transaction.TransactionRequest;
import az.ingress.bankapp.models.dto.transaction.TransactionResponse;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    PageResponse<TransactionResponse> getAllTransactions(Pageable pageable);

    TransactionResponse getTransactionById(Long transactionId);

}
