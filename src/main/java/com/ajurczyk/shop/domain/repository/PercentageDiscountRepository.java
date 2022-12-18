package com.ajurczyk.shop.domain.repository;

import com.ajurczyk.shop.domain.discounts.PercentageDiscount;
import com.ajurczyk.shop.infrastructure.repository.PercentageDiscountEntity;

import java.util.List;
import java.util.UUID;

public interface PercentageDiscountRepository {
    List<PercentageDiscountEntity> getProductDiscounts(UUID productId);

    UUID addDiscount(PercentageDiscount discount);

    List<PercentageDiscountEntity> getDiscounts();

}
