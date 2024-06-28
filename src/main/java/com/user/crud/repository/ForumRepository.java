package com.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.crud.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{
}
