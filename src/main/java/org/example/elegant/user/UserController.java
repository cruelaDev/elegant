package org.example.elegant.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.elegant.security.JwtService;
import org.example.elegant.user.dto.UserCreateDto;
import org.example.elegant.user.dto.UserResponseDto;
import org.example.elegant.user.dto.UserSignInDto;
import org.example.elegant.user.dto.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto createDto) throws IOException {
        UserResponseDto responseDto = userService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<UserResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<UserResponseDto> all = userService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable UUID id) {
        UserResponseDto responseDto = userService.get(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID id,
                                                  @RequestBody UserUpdateDto updateDto) {
        UserResponseDto responseDto = userService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDto> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> signUp(
            @RequestBody @Valid UserCreateDto userCreateDto
    ) throws IOException {
        UserResponseDto userResponseDto = userService.create(userCreateDto);
        String token = jwtService.generateToken(userResponseDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> singIn(
            @RequestBody @Valid UserSignInDto signInDto
    ) {
        UserResponseDto userResponseDto = userService.signIn(signInDto);
        String token = jwtService.generateToken(userResponseDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }
}
