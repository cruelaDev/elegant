package org.example.elegant.validation;

import lombok.RequiredArgsConstructor;
import org.example.elegant.user.UserRepository;
import org.example.elegant.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;

    public boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$", password);

    }

    public boolean isValidEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z0-9_! #$%&'*+/=?`{|}~^. -]+@[a-zA-Z0-9. -]+$", email);
    }
}
