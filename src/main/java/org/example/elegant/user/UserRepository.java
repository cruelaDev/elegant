package org.example.elegant.user;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CommonRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
