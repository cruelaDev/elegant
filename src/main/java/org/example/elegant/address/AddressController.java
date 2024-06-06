package org.example.elegant.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.elegant.address.dto.AddressCreateDto;
import org.example.elegant.address.dto.AddressResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @PreAuthorize("hasAnyAuthority('address:create')")
    @PostMapping("/create")
    public ResponseEntity<AddressResponseDto> create(@RequestBody @Valid AddressCreateDto addressCreateDto) throws IOException {
        AddressResponseDto addressResponseDto = service.create(addressCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('address:read')")
    @GetMapping("/getAll")
    public ResponseEntity<Page<AddressResponseDto>> get(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<AddressResponseDto> addressResponseDtos = service.getAll(predicate, pageable);
        return ResponseEntity.ok(addressResponseDtos);
    }

    @PreAuthorize("hasAnyAuthority('address:read')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<AddressResponseDto> get(@PathVariable UUID id) {
        AddressResponseDto addressResponseDto = service.get(id);
        return ResponseEntity.ok(addressResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('address:delete')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<AddressResponseDto> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
