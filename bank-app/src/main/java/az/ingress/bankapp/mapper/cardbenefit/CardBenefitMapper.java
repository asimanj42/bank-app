package az.ingress.bankapp.mapper.cardbenefit;

import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitRequest;
import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitResponse;
import az.ingress.bankapp.entity.CardBenefit;
import org.springframework.stereotype.Component;

@Component
public class CardBenefitMapper {

    public CardBenefitResponse cardBenefitEntityToResponse(CardBenefit cardBenefit) {
        return CardBenefitResponse.builder()
                .id(cardBenefit.getId())
                .name(cardBenefit.getName())
                .description(cardBenefit.getDescription())
                .cardId(cardBenefit.getCard().getId())
                .build();
    }

    public CardBenefit cardBenefitRequestToEntity(CardBenefitRequest cardBenefitRequest) {
        return CardBenefit.builder()
                .name(cardBenefitRequest.getName())
                .description(cardBenefitRequest.getDescription())
                .build();
    }

}
