package az.ingress.bankapp.models.dto.cardbenefit;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardBenefitRequest {

    private String name;
    private String description;
    private Long cardId;
}
