package com.ajurczyk.shop.domain.repository;

import com.ajurczyk.shop.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> getProduct(UUID productId);
}
