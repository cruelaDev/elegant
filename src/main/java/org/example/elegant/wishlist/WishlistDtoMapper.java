package org.example.elegant.wishlist;
import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.wishlist.dto.WishlistCreateDto;
import org.example.elegant.wishlist.dto.WishlistResponseDto;
import org.example.elegant.wishlist.dto.WishlistUpdateDto;
import org.example.elegant.wishlist.entity.Wishlist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistDtoMapper extends CommonMapper<Wishlist, WishlistCreateDto, WishlistResponseDto, WishlistUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Wishlist toEntity(WishlistCreateDto wishlistCreateDto) {
        return mapper.map(wishlistCreateDto,Wishlist.class);
    }

    @Override
    public WishlistResponseDto toResponseDto(Wishlist wishlist) {
        return mapper.map(wishlist, WishlistResponseDto.class);
    }

    @Override
    public void toEntity(WishlistUpdateDto wishlistUpdateDto, Wishlist wishlist) {
        mapper.map(wishlistUpdateDto,wishlist);
    }
}
