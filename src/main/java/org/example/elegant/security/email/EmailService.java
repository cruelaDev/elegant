package org.example.elegant.security.email;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.exceptions.IncorrectEmail;
import org.example.elegant.common.exceptions.IncorrectPassword;
import org.example.elegant.common.exceptions.TimeOut;
import org.example.elegant.security.email.dto.EmailDto;
import org.example.elegant.user.UserDtoMapper;
import org.example.elegant.user.UserRepository;
import org.example.elegant.user.dto.UserCreateDto;
import org.example.elegant.user.entity.User;
import org.example.elegant.user.role.RoleRepository;
import org.example.elegant.user.role.entity.Role;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.example.elegant.common.ExcMessage.*;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisTemplate<String, EmailDto> redisTemplate;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleRepository roleRepository;

    public String verifyEmail(EmailDto emailDto) {
        EmailDto email = redisTemplate.opsForValue().get(emailDto.getEmail());
        if (email != null) {
            String checkEmail = email.getUserCreateDto().getEmail();
            if (checkEmail.equals(email.getEmail())) {
                if (email.getMessage().equals(emailDto.getMessage())) {
                    UserCreateDto createDto = email.getUserCreateDto();
                    User user = userDtoMapper.toEntity(createDto);
                    user.setId(UUID.randomUUID());
                    Set<Role> roles = Collections.singleton(roleRepository.findByName("USER").orElseThrow());
                    user.setRoles(roles);
                    user.setVerified(true);
                    userRepository.save(user);
                    return SUCCESSFULLY_VERIFICATION;
                }
                throw new IncorrectPassword(INCORRECT_EMAIL_VER);
            }
            throw new IncorrectEmail(INCORRECT_EMAIL);
        }
        throw new TimeOut(TIME_OUT);

    }


    public void retry(EmailDto emailDto) {

    }
}
