package com.ajurczyk.shop.domain.repository;

import com.ajurczyk.shop.domain.discounts.AmountDiscount;
import com.ajurczyk.shop.infrastructure.repository.AmountDiscountEntity;

import java.util.List;
import java.util.UUID;

public interface AmountDiscountRepository {
    List<AmountDiscountEntity> getProductDiscounts(UUID productId);

    UUID addDiscount(AmountDiscount discount);

    List<AmountDiscountEntity> getDiscounts();
}
