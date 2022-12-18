package com.ajurczyk.shop.infrastructure.rest;

import com.ajurczyk.shop.domain.service.CheckoutService;
import com.ajurczyk.shop.infrastructure.rest.dto.CalculatedPriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/checkout", produces = APPLICATION_JSON_VALUE)
public class CheckoutController {

    private final CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<CalculatedPriceResponse> calculatePrice(@RequestParam(name = "product-id") UUID productId,
                                                                  @RequestParam int amount) {
        final BigDecimal priceAfterDiscounts = checkoutService.calculatePrice(productId, amount);
        return ResponseEntity.ok(new CalculatedPriceResponse(priceAfterDiscounts));
    }
}
