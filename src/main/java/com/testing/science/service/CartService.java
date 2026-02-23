package com.testing.science.service;

import com.testing.science.dto.CartDto;
import com.testing.science.dto.CartItemDto;
import com.testing.science.dto.UpdateCartItemDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface CartService {
    CartDto create();

    CartItemDto addToCart(UUID cartId, Long productId);

    List<CartDto> getAllCarts();

    CartDto getById(UUID cartId);

    CartDto update(UUID cartId, Long productId, @Valid UpdateCartItemDto cartItemDto);

    void deleteItemFromCart(UUID cartId, Long productId);

    void clearCart(UUID cartId);
}
