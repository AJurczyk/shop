package com.ajurczyk.shop.domain.service;

import com.ajurczyk.shop.domain.Product;
import com.ajurczyk.shop.domain.discounts.AmountDiscount;
import com.ajurczyk.shop.domain.discounts.PercentageDiscount;
import com.ajurczyk.shop.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckoutServiceTest {

    private ProductRepository productRepository;

    private DiscountService discountService;
    private CheckoutService checkoutService;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        discountService = mock(DiscountService.class);
        checkoutService = new CheckoutService(productRepository, discountService);
    }

    @Test
    void shouldReturnPriceWithoutAnyDiscount() {
        //given
        BigDecimal expected = BigDecimal.valueOf(50).setScale(2);
        UUID productUuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        when(productRepository.getProduct(any())).thenReturn(Optional.of(new Product(productUuid, "table", BigDecimal.TEN)));
        when(discountService.getApplicableDiscounts(any())).thenReturn(Collections.emptyList());

        //when
        BigDecimal actual = checkoutService.calculatePrice(productUuid, 5);

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void shouldChooseAmountDiscount() {
        //given
        BigDecimal expected = BigDecimal.valueOf(35).setScale(2);
        UUID productUuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        AmountDiscount amountDiscount = new AmountDiscount(BigDecimal.valueOf(50), 3, List.of(productUuid));
        PercentageDiscount percentageDiscount = new PercentageDiscount(BigDecimal.TEN, List.of(productUuid));
        when(productRepository.getProduct(any())).thenReturn(Optional.of(new Product(productUuid, "table", BigDecimal.TEN)));
        when(discountService.getApplicableDiscounts(any())).thenReturn(List.of(amountDiscount, percentageDiscount));

        //when
        BigDecimal actual = checkoutService.calculatePrice(productUuid, 5);

        //then
        assertThat(actual, is(equalTo(expected)));
    }


    @Test
    void shouldChoosePercentageDiscount() {
        //given
        BigDecimal expected = BigDecimal.valueOf(45).setScale(2);
        UUID productUuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        AmountDiscount amountDiscount = new AmountDiscount(BigDecimal.TEN, 2, List.of(productUuid));
        PercentageDiscount percentageDiscount = new PercentageDiscount(BigDecimal.TEN, List.of(productUuid));
        when(productRepository.getProduct(any())).thenReturn(Optional.of(new Product(productUuid, "table", BigDecimal.TEN)));
        when(discountService.getApplicableDiscounts(any())).thenReturn(List.of(amountDiscount, percentageDiscount));

        //when
        BigDecimal actual = checkoutService.calculatePrice(productUuid, 5);

        //then
        assertThat(actual, is(equalTo(expected)));
    }

}
