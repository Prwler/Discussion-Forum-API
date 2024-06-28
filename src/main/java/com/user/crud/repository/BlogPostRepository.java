package com.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.crud.model.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    
}
