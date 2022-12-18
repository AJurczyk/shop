package com.ajurczyk.shop.domain.service;

import com.ajurczyk.shop.common.BadRequestException;
import com.ajurczyk.shop.common.NotFoundException;
import com.ajurczyk.shop.domain.Product;
import com.ajurczyk.shop.domain.discounts.Discount;
import com.ajurczyk.shop.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CheckoutService {
    private final ProductRepository productRepository;

    private final DiscountService discountService;

    public BigDecimal calculatePrice(UUID productId, int quantity) {
        if (quantity <= 0 ) {
            throw new BadRequestException("Quantity of products must be more than 0");
        }
        final Product product = productRepository.getProduct(productId)
                        .orElseThrow(() -> new NotFoundException("Cannot find product with id: %s".formatted(productId.toString())));
        final BigDecimal productPrice = product.getPrice();
        final List<Discount> discounts = discountService.getApplicableDiscounts(productId);
        if (discounts.isEmpty()) {
            return productPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
        } else {
            return getBestDiscount(quantity, productPrice, discounts);
        }
    }

    private BigDecimal getBestDiscount(int quantity, BigDecimal productPrice, List<Discount> discounts) {
        return discounts.stream().map(d -> d.getTotalPriceAfterDiscount(productPrice, quantity))
                .min(BigDecimal::compareTo)
                .orElseThrow();
    }
}
/*
ASSUMPTIONS:
- discounts don't accumulate
- if multiple discounts are applicable then we choose most valuable
 */
