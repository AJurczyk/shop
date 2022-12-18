package com.ajurczyk.shop.domain.discounts;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal getTotalPriceAfterDiscount(BigDecimal productPrice, int quantity);
}
