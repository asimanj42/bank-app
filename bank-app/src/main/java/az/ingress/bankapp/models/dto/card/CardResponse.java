package az.ingress.bankapp.models.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponse {

    private String cardNumber;
    private String cardType;
    private String expirationDate;
    private Long accountId;
}
