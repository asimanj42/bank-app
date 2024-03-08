package az.ingress.bankapp.service.card;

import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.models.dto.card.CardRequest;
import az.ingress.bankapp.models.dto.card.CardResponse;
import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.mapper.card.CardMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.*;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final CardMapper cardMapper;

    @Override
    public CardResponse createCard(CardRequest cardRequest) {
        checkCardNumberExisting(cardRequest.getCardNumber());
        Card card = cardMapper.cardRequestToEntity(cardRequest);
        card.setAccount(
                accountRepository.findById(cardRequest.getAccountId()).orElseThrow(
                        () -> BaseException.of(ACCOUNT_NOT_FOUND))
        );
        Card savedCard = cardRepository.save(card);
        return getCardResponse(savedCard);
    }

    @Override
    public CardResponse getCardByNumber(String cardNumber) {
        Card card = checkCardExistingGivenCardNumber(cardNumber);
        return getCardResponse(card);
    }


    @Override
    public CardResponse updateCard(CardRequest cardRequest, Long cardId) {
        Card card = checkCardExistingGivenId(cardId);
        Card updatedCard = updateIfNotNull(cardRequest, card);
        updatedCard.setId(cardId);
        Card savedCard = cardRepository.save(updatedCard);
        return getCardResponse(savedCard);
    }

    @Override
    public CardResponse deleteCard(Long cardId) {
        Card card = checkCardExistingGivenId(cardId);
        cardRepository.delete(card);
        return getCardResponse(card);
    }

    private void checkCardNumberExisting(String cardNumber) {
        if (cardRepository.existsByCardNumber(cardNumber)) {
            throw BaseException.of(CARD_ALREADY_EXISTS);
        }
    }

    private CardResponse getCardResponse(Card savedCard) {
        return cardMapper.cardEntityToResponse(savedCard);
    }

    private Card checkCardExistingGivenCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> BaseException.of(CARD_NOT_FOUND)
        );
    }


    private Card checkCardExistingGivenId(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(
                () -> BaseException.of(CARD_NOT_FOUND)
        );
    }

    private Card updateIfNotNull(CardRequest cardRequest, Card card) {
        if (cardRequest.getCardNumber() != null) {
            card.setCardNumber(cardRequest.getCardNumber());
        }
        if (cardRequest.getCardType() != null) {
            card.setCardType(cardRequest.getCardType());
        }
        if (cardRequest.getExpirationDate() != null) {
            card.setExpirationDate(cardRequest.getExpirationDate());
        }
        return card;
    }
}
