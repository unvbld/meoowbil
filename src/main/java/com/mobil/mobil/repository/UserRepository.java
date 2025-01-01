package com.mobil.mobil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobil.mobil.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}