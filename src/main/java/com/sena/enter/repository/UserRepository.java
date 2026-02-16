package com.sena.enter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.enter.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "authorities")
    Optional<User> findOneByLogin(String login);

    Optional<User> findByActivationKey(String key);

    Optional<User> findByResetKey(String key);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);
}
