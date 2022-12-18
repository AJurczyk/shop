package com.ajurczyk.shop.infrastructure.repository;

import com.ajurczyk.shop.domain.discounts.AmountDiscount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AmountDiscountEntity {
    private UUID id;
    BigDecimal nextItemDiscountPercentage;

    int maxQuantity;

    List<UUID> productIds = new ArrayList<>();

    public AmountDiscountEntity(BigDecimal nextItemDiscountPercentage, int maxQuantity, List<UUID> productIds) {
        this.nextItemDiscountPercentage = nextItemDiscountPercentage;
        this.maxQuantity = maxQuantity;
        this.productIds = productIds;
    }

    public AmountDiscount toAmountDiscount() {
        return new AmountDiscount(nextItemDiscountPercentage, maxQuantity, productIds);
    }
}
