package com.ajurczyk.shop.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddAmountDiscountRequest {
    private double nextItemDiscountPercentage;
    private int maxQuantity;
    private List<UUID> productIds;
}
