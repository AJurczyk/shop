package com.ajurczyk.shop.domain.discounts;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Data
public class PercentageDiscount implements Discount {
    private final BigDecimal percentageMultiplier;
    private final BigDecimal percentage;
    private final List<UUID> productIds;

    public PercentageDiscount(BigDecimal percentage, List<UUID> productIds) {
        this.percentage = percentage;
        this.percentageMultiplier = BigDecimal.ONE.subtract(percentage.divide(BigDecimal.valueOf(100)));
        this.productIds = productIds;
    }

    @Override
    public BigDecimal getTotalPriceAfterDiscount(BigDecimal productPrice, int quantity) {
        return productPrice.multiply(percentageMultiplier).setScale(2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(quantity));
    }
}
