package az.ingress.bankapp.repository;

import az.ingress.bankapp.entity.CardBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBenefitRepository extends JpaRepository<CardBenefit, Long> {
}
