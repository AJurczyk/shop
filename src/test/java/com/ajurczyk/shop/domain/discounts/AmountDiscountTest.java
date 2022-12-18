package com.ajurczyk.shop.domain.discounts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountDiscountTest {

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void shouldCalculateCorrectDiscount(BigDecimal nextItemDiscountPercentage, int maxQuantity, BigDecimal productPrice, int quantity, BigDecimal expectedTotalPrice) {
        // given
        AmountDiscount discount = new AmountDiscount(nextItemDiscountPercentage, maxQuantity, List.of(
                UUID.fromString("4591ae89-606f-4076-baf6-8822282e076e")));

        // when
        BigDecimal result = discount.getTotalPriceAfterDiscount(productPrice, quantity);

        // then
        assertEquals(expectedTotalPrice, result);
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                        Arguments.of(BigDecimal.valueOf(10), 6, BigDecimal.valueOf(5), 5, BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_UP)),
                        Arguments.of(BigDecimal.valueOf(10), 3, BigDecimal.valueOf(5), 5, BigDecimal.valueOf(23.5).setScale(2, RoundingMode.HALF_UP)),
                        Arguments.of(BigDecimal.valueOf(20), 2, BigDecimal.valueOf(100), 3, BigDecimal.valueOf(280).setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(BigDecimal.valueOf(20), 3, BigDecimal.valueOf(100), 3, BigDecimal.valueOf(240).setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(BigDecimal.valueOf(10), 10, BigDecimal.valueOf(1.5), 10, BigDecimal.valueOf(8.25).setScale(2, RoundingMode.HALF_UP))
        );
    }
}

