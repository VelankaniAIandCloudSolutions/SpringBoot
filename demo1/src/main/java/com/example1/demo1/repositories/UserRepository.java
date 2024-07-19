package com.example1.demo1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example1.demo1.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}