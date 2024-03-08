package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitRequest;
import az.ingress.bankapp.models.dto.cardbenefit.CardBenefitResponse;
import az.ingress.bankapp.service.cardbenefit.CardBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cardbenefits")
@RequiredArgsConstructor
public class CardBenefitController {

    private final CardBenefitService cardBenefitService;

    @GetMapping("/{cardBenefitId}")
    @ResponseStatus(HttpStatus.OK)
    public CardBenefitResponse getCardBenefitById(@PathVariable("cardBenefitId") Long cardBenefitId) {
        return cardBenefitService.getCardBenefitById(cardBenefitId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardBenefitResponse createCardBenefit(@RequestBody CardBenefitRequest cardBenefitRequest) {
        return cardBenefitService.createCardBenefit(cardBenefitRequest);
    }

    @PutMapping("/{cardBenefitId}")
    @ResponseStatus(HttpStatus.OK)
    public CardBenefitResponse updateCardBenefit(@RequestBody CardBenefitRequest cardBenefitRequest, @PathVariable("cardBenefitId") Long cardBenefitId) {
        return cardBenefitService.updateCardBenefit(cardBenefitRequest, cardBenefitId);
    }

    @DeleteMapping("/{cardBenefitId}")
    @ResponseStatus(HttpStatus.OK)
    public CardBenefitResponse deleteCardBenefit(@PathVariable("cardBenefitId") Long cardBenefitId) {
        return cardBenefitService.deleteCardBenefit(cardBenefitId);
    }
}
