package com.ajurczyk.shop.infrastructure.repository;

import com.ajurczyk.shop.domain.Product;
import com.ajurczyk.shop.domain.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryInMemory implements ProductRepository {
    final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<Product> getProduct(UUID productId) {
        return products.stream()
                        .filter(p -> p.getId().equals(productId))
                        .findFirst();
    }

    @PostConstruct
    private void fillProducts() {
        products.addAll(List.of(
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000001"), "apple", BigDecimal.valueOf(10)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000002"), "pear", BigDecimal.valueOf(4.99)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000003"), "orange", BigDecimal.valueOf(0.99)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000004"), "table", BigDecimal.valueOf(20)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000005"), "tv", BigDecimal.valueOf(4099)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000006"), "keyboard", BigDecimal.valueOf(233)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000007"), "window", BigDecimal.valueOf(12)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000008"), "wallet", BigDecimal.valueOf(100.13)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000009"), "mug", BigDecimal.valueOf(1.99)),
                        new Product(UUID.fromString("00000000-0000-0000-0000-000000000010"), "plate", BigDecimal.valueOf(2))
        ));
    }
}
