package com.ajurczyk.shop.infrastructure.rest;

import com.ajurczyk.shop.domain.service.DiscountService;
import com.ajurczyk.shop.infrastructure.rest.dto.AddAmountDiscountRequest;
import com.ajurczyk.shop.infrastructure.rest.dto.AddPercentageDiscountRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/discounts", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/percentage-discount")
    public UUID addPercentageDiscount(@RequestBody AddPercentageDiscountRequest request) {
        return discountService.addPercentageDiscount(request);
    }

    @PostMapping("/amount-discount")
    public UUID addAmountDiscount(@RequestBody AddAmountDiscountRequest request) {
        return discountService.addAmountDiscount(request);
    }
}
