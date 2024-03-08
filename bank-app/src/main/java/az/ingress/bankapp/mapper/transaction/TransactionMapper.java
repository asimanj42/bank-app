package az.ingress.bankapp.mapper.transaction;

import az.ingress.bankapp.models.dto.transaction.TransactionRequest;
import az.ingress.bankapp.models.dto.transaction.TransactionResponse;
import az.ingress.bankapp.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction transactionRequestToEntity(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .type(transactionRequest.getTransactionType())
                .amount(transactionRequest.getAmount())
                .date(transactionRequest.getTransactionDate())
                .build();
    }

    public TransactionResponse transactionEntityToResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionType(transaction.getType())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getDate())
                .fromAccountId(transaction.getFromAccount().getId())
                .toAccountId(transaction.getToAccount().getId())
                .build();
    }
}
