package org.example.elegant.order;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.order.dto.OrderCreateDto;
import org.example.elegant.order.dto.OrderResponseDto;
import org.example.elegant.order.dto.OrderUpdateDto;
import org.example.elegant.order.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderDtoMapper extends CommonMapper<Order, OrderCreateDto, OrderResponseDto, OrderUpdateDto> {
    private final ModelMapper mapper;

    @Override
    public Order toEntity(OrderCreateDto orderCreateDto) {
        return mapper.map(orderCreateDto, Order.class);
    }

    @Override
    public OrderResponseDto toResponseDto(Order order) {
        return mapper.map(order, OrderResponseDto.class);

    }

    @Override
    public void toEntity(OrderUpdateDto orderUpdateDto, Order order) {
        mapper.map(orderUpdateDto, order);
    }
}
