package com.ajurczyk.shop.infrastructure.repository;

import com.ajurczyk.shop.domain.discounts.PercentageDiscount;
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
public class PercentageDiscountEntity {
    private UUID id;
    private BigDecimal percentage;
    private List<UUID> productIds = new ArrayList<>();

    public PercentageDiscount toPercentageDiscount() {
        return new PercentageDiscount(percentage, productIds);
    }
}
