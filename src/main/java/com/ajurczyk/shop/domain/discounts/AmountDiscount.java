package com.ajurczyk.shop.domain.discounts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@AllArgsConstructor
@Data
public class AmountDiscount implements Discount {

    private final BigDecimal nextItemDiscountPercentage;

    private int maxQuantity;
    private List<UUID> productIds;

    @Override
    public BigDecimal getTotalPriceAfterDiscount(BigDecimal productPrice, int quantity) {
        int productsOnDiscount = quantity > maxQuantity ?
                        maxQuantity - 1 :
                        quantity - 1;
        BigDecimal singleDiscountValue = productPrice
                        .multiply(nextItemDiscountPercentage
                                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
        int discountsAmount = IntStream.rangeClosed(1, productsOnDiscount)
                        .reduce(Integer::sum)
                        .orElseThrow();
        BigDecimal totalPriceBeforeDiscount = productPrice.multiply(BigDecimal.valueOf(quantity));

        BigDecimal totalDiscount = BigDecimal.valueOf(discountsAmount)
                .multiply(singleDiscountValue);
        return totalPriceBeforeDiscount.subtract(totalDiscount).setScale(2, RoundingMode.HALF_UP);
    }
}
