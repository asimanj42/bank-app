package az.ingress.bankapp.mapper.card;


import az.ingress.bankapp.models.dto.card.CardRequest;
import az.ingress.bankapp.models.dto.card.CardResponse;
import az.ingress.bankapp.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardResponse cardEntityToResponse(Card card) {
        return CardResponse.builder()
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .expirationDate(card.getExpirationDate())
                .accountId(card.getAccount().getId())
                .build();
    }

    public Card cardRequestToEntity(CardRequest cardRequest) {
        return Card.builder()
                .cardNumber(cardRequest.getCardNumber())
                .cardType(cardRequest.getCardType())
                .expirationDate(cardRequest.getExpirationDate())
                .build();
    }
}
