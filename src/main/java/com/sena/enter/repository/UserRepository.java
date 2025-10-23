package com.sena.enter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.enter.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
