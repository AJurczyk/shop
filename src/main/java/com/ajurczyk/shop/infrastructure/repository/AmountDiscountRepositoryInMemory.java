package com.ajurczyk.shop.infrastructure.repository;

import com.ajurczyk.shop.domain.discounts.AmountDiscount;
import com.ajurczyk.shop.domain.repository.AmountDiscountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class AmountDiscountRepositoryInMemory implements AmountDiscountRepository {
    final List<AmountDiscountEntity> amountDiscounts = new ArrayList<>();


    @Override
    public List<AmountDiscountEntity> getProductDiscounts(UUID productId) {
        return amountDiscounts.stream()
                        .filter(d -> d.getProductIds().contains(productId))
                        .toList();
    }

    @Override
    public UUID addDiscount(AmountDiscount discount) {
        final UUID uuid = UUID.randomUUID();
        AmountDiscountEntity entity = new AmountDiscountEntity(uuid, discount.getNextItemDiscountPercentage(),
                discount.getMaxQuantity(), discount.getProductIds());
        amountDiscounts.add(entity);
        return uuid;
    }

    @Override
    public List<AmountDiscountEntity> getDiscounts() {
        return amountDiscounts;
    }


    @PostConstruct
    private void fillDiscounts() {
        amountDiscounts.addAll(List.of(
                        new AmountDiscountEntity(BigDecimal.valueOf(10), 5, List.of(UUID.fromString("00000000-0000-0000-0000-000000000001")))
        ));
    }
}
