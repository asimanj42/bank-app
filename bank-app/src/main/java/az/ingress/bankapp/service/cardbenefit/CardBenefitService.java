package az.ingress.bankapp.service.cardbenefit;

import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitRequest;
import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitResponse;

public interface CardBenefitService {

    CardBenefitResponse createCardBenefit(CardBenefitRequest cardBenefitRequest);

    CardBenefitResponse getCardBenefitById(Long cardBenefitId);

    CardBenefitResponse updateCardBenefit(CardBenefitRequest cardBenefitRequest, Long cardBenefitId);

    CardBenefitResponse deleteCardBenefit(Long cardBenefitId);
}
