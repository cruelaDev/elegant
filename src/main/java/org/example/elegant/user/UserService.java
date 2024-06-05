package org.example.elegant.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.elegant.common.exceptions.WrongInputException;
import org.example.elegant.common.service.CommonService;
import org.example.elegant.security.email.NotificationService;
import org.example.elegant.security.email.dto.EmailDto;
import org.example.elegant.security.otp.OtpRepository;
import org.example.elegant.user.dto.UserCreateDto;
import org.example.elegant.user.dto.UserResponseDto;
import org.example.elegant.user.dto.UserSignInDto;
import org.example.elegant.user.dto.UserUpdateDto;
import org.example.elegant.user.entity.User;
import org.example.elegant.user.role.RoleRepository;
import org.example.elegant.validation.UserValidation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.example.elegant.common.ExcMessage.*;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends CommonService<User, UUID, UserCreateDto, UserResponseDto, UserUpdateDto> implements UserDetailsService {
    private final UserRepository repository;
    private final Class<User> entityClass = User.class;
    private final UserDtoMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;
    private final NotificationService notificationService;
    private final Random random = new Random();
    private final RedisTemplate<String, EmailDto> redisTemplate;
    private final UserValidation validation;

    @Override
    protected UserResponseDto internalCreate(UserCreateDto createDto) {

        if (!validation.isValidPassword(createDto.getPassword())) {
            throw new WrongInputException(INVALID_PASSWORD);
        }
        if (!validation.isValidEmail(createDto.getEmail())) {
            throw new WrongInputException(INVALID_EMAIL);
        }

        int code = random.nextInt(1000, 10000);
        notificationService.sendVerificationCode(createDto.getEmail(), code);
        createDto.setPassword(passwordEncoder.encode(createDto.getPassword()));
        EmailDto emailDto = new EmailDto(createDto.getEmail(), String.valueOf(code),createDto);
        redisTemplate.opsForValue().set(createDto.getEmail(), emailDto, 5, TimeUnit.MINUTES);
        return mapper.userResponseDto(createDto);
    }

    @Override
    protected UserResponseDto internalUpdate(UUID uuid, UserUpdateDto userUpdateDto) {
        return null;
    }

    @Transactional
    public UserResponseDto signIn(UserSignInDto signInDto) {
        User user = repository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email or password is not correct"));

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Email or password is not correct");
        }

        return mapper.toResponseDto(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS));
    }
}
