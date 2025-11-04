package com.sena.enter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.enter.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLog(String login);
    Optional<User> findByActivationKey(String key);
    Optional<User> findByResetKey(String key);
    Optional<User> findByEma(String email);
}
