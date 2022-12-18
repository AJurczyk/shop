package com.ajurczyk.shop.domain.service;

import com.ajurczyk.shop.domain.discounts.AmountDiscount;
import com.ajurczyk.shop.domain.discounts.Discount;
import com.ajurczyk.shop.domain.discounts.PercentageDiscount;
import com.ajurczyk.shop.domain.repository.AmountDiscountRepository;
import com.ajurczyk.shop.domain.repository.PercentageDiscountRepository;
import com.ajurczyk.shop.infrastructure.repository.AmountDiscountEntity;
import com.ajurczyk.shop.infrastructure.repository.PercentageDiscountEntity;
import com.ajurczyk.shop.infrastructure.rest.dto.AddAmountDiscountRequest;
import com.ajurczyk.shop.infrastructure.rest.dto.AddPercentageDiscountRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class DiscountService {
    private final AmountDiscountRepository amountDiscountRepository;
    private final PercentageDiscountRepository percentageDiscountRepository;

    public List<Discount> getApplicableDiscounts(UUID productId) {
        return Stream.concat(getAmountDiscounts(productId), getPercentageDiscounts(productId)).toList();
    }

    private Stream<AmountDiscount> getAmountDiscounts(UUID productId) {
        return amountDiscountRepository.getProductDiscounts(productId)
                .stream()
                .map(AmountDiscountEntity::toAmountDiscount);
    }

    private Stream<PercentageDiscount> getPercentageDiscounts(UUID productId) {
        return percentageDiscountRepository.getProductDiscounts(productId).stream()
                .map(PercentageDiscountEntity::toPercentageDiscount);
    }

    public UUID addPercentageDiscount(AddPercentageDiscountRequest request) {
        final BigDecimal percentage = BigDecimal.valueOf(request.getPercentage());
        return percentageDiscountRepository.addDiscount(new PercentageDiscount(percentage, request.getProductIds()));
    }

    public UUID addAmountDiscount(AddAmountDiscountRequest request) {
        final BigDecimal percentage = BigDecimal.valueOf(request.getNextItemDiscountPercentage());
        return amountDiscountRepository.addDiscount(new AmountDiscount(percentage, request.getMaxQuantity(), request.getProductIds()));
    }
}
