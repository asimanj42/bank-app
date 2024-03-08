package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.card.CardRequest;
import az.ingress.bankapp.models.dto.card.CardResponse;
import az.ingress.bankapp.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse getCardByNumber(@PathVariable("cardNumber") String cardNumber) {
        return cardService.getCardByNumber(cardNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse createCard(@RequestBody CardRequest cardRequest) {
        return cardService.createCard(cardRequest);
    }

    @PutMapping("/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse updateCard(@RequestBody CardRequest cardRequest, @PathVariable("cardId") Long cardId) {
        return cardService.updateCard(cardRequest, cardId);
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse deleteCard(@PathVariable("cardId") Long cardId) {
        return cardService.deleteCard(cardId);
    }



}
