package com.testing.science.controller;

import com.testing.science.dto.AddItemToCartRequest;
import com.testing.science.dto.CartDto;
import com.testing.science.dto.CartItemDto;
import com.testing.science.dto.UpdateCartItemDto;
import com.testing.science.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(){
        var cart = cartService.create();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(@PathVariable UUID cartId,
                                                 @RequestBody AddItemToCartRequest request){
        var cartItem = cartService.addToCart(cartId,request.productId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(cartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCart(){
        var cart = cartService.getAllCarts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(cart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable UUID cartId){
        var cart = cartService.getById(cartId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cart);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartDto> updateItem(@PathVariable UUID cartId,
                                              @PathVariable Long productId,
                                              @Valid @RequestBody UpdateCartItemDto cartItemDto){
        var cartItem = cartService.update(cartId,productId,cartItemDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cartItem);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable UUID cartId,
                                                @PathVariable Long productId){
        cartService.deleteItemFromCart(cartId,productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId){
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

}