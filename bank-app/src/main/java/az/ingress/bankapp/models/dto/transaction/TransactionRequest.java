package az.ingress.bankapp.models.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    private String transactionType;
    private double amount;
    private String transactionDate;
    private Long fromAccountId;
    private Long toAccountId;
}
