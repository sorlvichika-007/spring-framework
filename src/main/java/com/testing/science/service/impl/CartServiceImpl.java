package com.testing.science.service.impl;

import com.testing.science.dto.CartDto;
import com.testing.science.dto.CartItemDto;
import com.testing.science.dto.UpdateCartItemDto;
import com.testing.science.exceptions.ResourceNotFoundException;
import com.testing.science.mapper.CartMapper;
import com.testing.science.model.Cart;
import com.testing.science.repository.CartRepository;
import com.testing.science.repository.ProductRepository;
import com.testing.science.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @Override
    public CartDto create() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartItemDto addToCart(UUID cartId, Long productId) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        var product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toItemDto(cartItem);
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDto)
                .toList();
    }

    @Override
    public CartDto getById(UUID cartId) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
        return cartMapper.toDto(cart);
    }

    @Override
    public CartDto update(UUID cartId, Long productId, UpdateCartItemDto cartItemDto) {
        var cart = cartRepository.getCartWithItems(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        var cartItem = cart.getItemFromCart(productId);
        if (cartItem == null){
            throw new ResourceNotFoundException("Cart item not found");
        }
        cartItem.setQuantity(cartItemDto.quantity());
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public void deleteItemFromCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.clear();
        cartRepository.save(cart);
    }
}
