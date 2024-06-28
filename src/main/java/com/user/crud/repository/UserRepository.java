package com.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
