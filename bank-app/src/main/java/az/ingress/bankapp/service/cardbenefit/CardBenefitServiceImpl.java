package az.ingress.bankapp.service.cardbenefit;

import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitRequest;
import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitResponse;
import az.ingress.bankapp.entity.CardBenefit;
import az.ingress.bankapp.mapper.cardbenefit.CardBenefitMapper;
import az.ingress.bankapp.repository.CardBenefitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.CARD_BENEFIT_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class CardBenefitServiceImpl implements CardBenefitService {
    private final CardBenefitRepository cardBenefitRepository;
    private final CardBenefitMapper cardBenefitMapper;
    @Override
    public CardBenefitResponse createCardBenefit(CardBenefitRequest cardBenefitRequest) {
        CardBenefit cardBenefit = cardBenefitMapper.cardBenefitRequestToEntity(cardBenefitRequest);
        CardBenefit savedCardBenefit = cardBenefitRepository.save(cardBenefit);
        return getCardBenefitResponse(savedCardBenefit);
    }


    @Override
    public CardBenefitResponse getCardBenefitById(Long cardBenefitId) {
        CardBenefit cardBenefit = checkCardBenefitExistingGivenId(cardBenefitId);
        return getCardBenefitResponse(cardBenefit);
    }



    @Override
    public CardBenefitResponse updateCardBenefit(CardBenefitRequest cardBenefitRequest, Long cardBenefitId) {
        CardBenefit cardBenefit = checkCardBenefitExistingGivenId(cardBenefitId);
        CardBenefit updatedCardBenefit = updateIfNotNull(cardBenefitRequest, cardBenefit);
        CardBenefit savedCardBenefit = cardBenefitRepository.save(updatedCardBenefit);
        return getCardBenefitResponse(savedCardBenefit);
    }

    @Override
    public CardBenefitResponse deleteCardBenefit(Long cardBenefitId) {
        CardBenefit cardBenefit = checkCardBenefitExistingGivenId(cardBenefitId);
        cardBenefitRepository.delete(cardBenefit);
        return getCardBenefitResponse(cardBenefit);
    }

    private CardBenefit checkCardBenefitExistingGivenId(Long cardBenefitId) {
        return cardBenefitRepository.findById(cardBenefitId).orElseThrow(
                () -> BaseException.of(CARD_BENEFIT_NOT_FOUND)
        );
    }

    private CardBenefitResponse getCardBenefitResponse(CardBenefit savedCardBenefit) {
        return cardBenefitMapper.cardBenefitEntityToResponse(savedCardBenefit);
    }

    private CardBenefit updateIfNotNull(CardBenefitRequest cardBenefitRequest, CardBenefit cardBenefit) {
        if (cardBenefitRequest.getName() != null) {
            cardBenefit.setName(cardBenefitRequest.getName());
        }
        if (cardBenefitRequest.getDescription() != null) {
            cardBenefit.setDescription(cardBenefitRequest.getDescription());
        }
        return cardBenefit;
    }
}
