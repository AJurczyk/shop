package com.ajurczyk.shop.infrastructure.repository;

import com.ajurczyk.shop.domain.discounts.PercentageDiscount;
import com.ajurczyk.shop.domain.repository.PercentageDiscountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class PercentageDiscountRepositoryInMemory implements PercentageDiscountRepository {
    final List<PercentageDiscountEntity> percentageDiscounts = new ArrayList<>();

    @Override
    public List<PercentageDiscountEntity> getProductDiscounts(UUID productId) {
        return percentageDiscounts.stream()
                        .filter(d -> d.getProductIds().contains(productId))
                        .toList();
    }

    @Override
    public UUID addDiscount(PercentageDiscount discount) {
        final UUID uuid = UUID.randomUUID();
        PercentageDiscountEntity entity = new PercentageDiscountEntity(uuid, discount.getPercentage(), discount.getProductIds());
        percentageDiscounts.add(entity);
        return uuid;
    }

    @Override
    public List<PercentageDiscountEntity> getDiscounts() {
        return percentageDiscounts;
    }

    @PostConstruct
    private void fillDiscounts() {
        percentageDiscounts.addAll(List.of(
                                        new PercentageDiscountEntity(UUID.randomUUID(), BigDecimal.valueOf(10), List.of(UUID.fromString("00000000-0000-0000-0000-000000000002")))
                        )
        );
    }
}
