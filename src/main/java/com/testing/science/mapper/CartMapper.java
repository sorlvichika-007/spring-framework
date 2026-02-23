package com.testing.science.mapper;

import com.testing.science.dto.CartDto;
import com.testing.science.dto.CartItemDto;
import com.testing.science.model.Cart;
import com.testing.science.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice" , expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.totalPrice())")
    CartItemDto toItemDto(CartItem cartItem);
}
