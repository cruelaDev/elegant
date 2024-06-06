package org.example.elegant.address;

import lombok.RequiredArgsConstructor;
import org.example.elegant.address.dto.AddressCreateDto;
import org.example.elegant.address.dto.AddressResponseDto;
import org.example.elegant.address.entity.Address;
import org.example.elegant.common.mapper.CommonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoMapper extends CommonMapper<Address, AddressCreateDto, AddressResponseDto, AddressCreateDto> {
    private final ModelMapper mapper;

    @Override
    public Address toEntity(AddressCreateDto addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    @Override
    public AddressResponseDto toResponseDto(Address address) {
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public void toEntity(AddressCreateDto addressCreateDto, Address address) {
        mapper.map(addressCreateDto, address);
    }
}
