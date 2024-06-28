package com.user.crud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.crud.dto.BlogPostDto;
import com.user.crud.model.BlogPost;
import com.user.crud.services.BlogPostService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class BlogPostController {
    @Autowired
    BlogPostService blogPostService;

    @PostMapping(value = "/create_post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BlogPost> createBlogPost(
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postContent") String postContent,
            @RequestParam("forumId") Long forumId,
            @RequestParam("userId") Long userId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            BlogPostDto blogPostDto = BlogPostDto.builder()
                    .postTitle(postTitle)
                    .postContent(postContent)
                    .forumId(forumId)
                    .userId(userId)
                    .imageFile(imageFile)
                    .build();

            BlogPost createdPost = blogPostService.createBlogPost(blogPostDto);
            return ResponseEntity.ok(createdPost);
        } catch (ResponseStatusException e) {
            throw e; // Re-throw exceptions from the service layer
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating blog post", e);
        }
    }

    @DeleteMapping("/delete_post_by_id/{post_id}")
    public String deletePostById(@PathVariable Long post_id){
        return this.blogPostService.deletePostById(post_id);
    }

    @PutMapping("update_post_by_id/{post_id}")
    public BlogPost updatePost(@RequestBody BlogPostDto blogPostDto, @PathVariable Long post_id){
        BlogPost updatedPost = this.blogPostService.updatePostById(blogPostDto, post_id);
        return updatedPost;
    }
}
