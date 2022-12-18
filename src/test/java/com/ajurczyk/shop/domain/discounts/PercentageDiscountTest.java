package com.ajurczyk.shop.domain.discounts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountTest {
    @ParameterizedTest
    @MethodSource("testDataProvider")
    void shouldCalculateCorrectDiscount(BigDecimal percentage, BigDecimal productPrice, int quantity, BigDecimal expectedTotalPrice) {
        // given
        PercentageDiscount discount = new PercentageDiscount(percentage, List.of(UUID.fromString("4591ae89-606f-4076-baf6-8822282e076e")));

        // when
        BigDecimal result = discount.getTotalPriceAfterDiscount(productPrice, quantity);

        // then
        assertEquals(expectedTotalPrice, result);
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                        Arguments.of(BigDecimal.valueOf(10), BigDecimal.valueOf(5), 3, BigDecimal.valueOf(13.50).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(35), BigDecimal.valueOf(4.99), 10, BigDecimal.valueOf(32.4).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(90), BigDecimal.valueOf(20), 100, BigDecimal.valueOf(200).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(4), BigDecimal.valueOf(3.25), 100, BigDecimal.valueOf(312).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(20), BigDecimal.valueOf(38), 2, BigDecimal.valueOf(60.8).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(10), BigDecimal.valueOf(1400), 1, BigDecimal.valueOf(1260).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(15), BigDecimal.valueOf(120), 12, BigDecimal.valueOf(1224).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(10), BigDecimal.valueOf(378.4), 20, BigDecimal.valueOf(6811.2).setScale(2)),
                        Arguments.of(BigDecimal.valueOf(20), BigDecimal.valueOf(320.2), 10, BigDecimal.valueOf(2561.6).setScale(2))
        );
    }
}
