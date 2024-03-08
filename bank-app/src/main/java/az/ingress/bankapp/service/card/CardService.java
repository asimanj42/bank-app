package az.ingress.bankapp.service.card;

import az.ingress.bankapp.models.dto.card.CardRequest;
import az.ingress.bankapp.models.dto.card.CardResponse;

public interface CardService {

    CardResponse createCard(CardRequest cardRequest);

    CardResponse getCardByNumber(String cardNumber);

    CardResponse updateCard(CardRequest cardRequest, Long cardId);

    CardResponse deleteCard(Long cardId);
}
