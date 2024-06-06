package org.example.elegant.cart;

import lombok.RequiredArgsConstructor;
import org.example.elegant.cart.dto.CartCreateDto;
import org.example.elegant.cart.dto.CartResponseDto;
import org.example.elegant.cart.dto.CartUpdateDto;
import org.example.elegant.cart.entity.Cart;
import org.example.elegant.common.mapper.CommonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDtoMapper extends CommonMapper<Cart, CartCreateDto, CartResponseDto, CartUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
        return mapper.map(cartCreateDto,Cart.class);
    }

    @Override
    public CartResponseDto toResponseDto(Cart cart) {
        return mapper.map(cart, CartResponseDto.class);
    }

    @Override
    public void toEntity(CartUpdateDto cartUpdateDto, Cart cart) {
       mapper.map(cartUpdateDto,cart);
    }
}
