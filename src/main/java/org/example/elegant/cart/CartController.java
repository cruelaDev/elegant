package org.example.elegant.cart;

import lombok.RequiredArgsConstructor;
import org.example.elegant.cart.dto.CartCreateDto;
import org.example.elegant.cart.dto.CartResponseDto;
import org.example.elegant.cart.dto.CartUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
//    @PreAuthorize("hasAnyAuthority('cart:create')")
    @PostMapping("/create")
    public ResponseEntity<CartResponseDto> create(@RequestBody CartCreateDto cartCreateDto) throws IOException {
        CartResponseDto cartResponseDto = cartService.create(cartCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponseDto);
    }
//    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/getById/{id}")
    public ResponseEntity<CartResponseDto> getId(@PathVariable UUID id){
        CartResponseDto cartResponseDto = cartService.get(id);
        return ResponseEntity.ok(cartResponseDto);
    }
//    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/getAll")
    public ResponseEntity<Page<CartResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<CartResponseDto> all = cartService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }
    @PreAuthorize("hasAnyAuthority('commit:update')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<CartResponseDto> update(@PathVariable UUID id, @RequestBody CartUpdateDto updateDTO){
        CartResponseDto update = cartService.update(id, updateDTO);
        return ResponseEntity.ok(update);
    }
    @PreAuthorize("hasAnyAuthority('commit:delete')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
