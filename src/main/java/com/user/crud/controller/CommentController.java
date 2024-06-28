package com.user.crud.controller;

import com.user.crud.dto.BlogPostDto;
import com.user.crud.dto.CommentDto;
import com.user.crud.model.BlogPost;
import com.user.crud.model.Comment;
import com.user.crud.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping(value="/create_comment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Comment> createComment(
            @RequestParam("content") String content,
            @RequestParam("postId") Long postId,
            @RequestParam("userId") Long userId,
            @RequestParam(value = "imageFile", required = false)MultipartFile imageFile) {
        try {
            CommentDto commentDto = CommentDto.builder()
                    .content(content)
                    .postId(postId)
                    .userId(userId)
                    .imageFile(imageFile)
                    .build();
            Comment createdComment = commentService.createComment(commentDto);
            return ResponseEntity.ok(createdComment);
        } catch (ResponseStatusException e){
            throw e;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating comment", e);
        }
    }
    @GetMapping("/get_comment_by_id/{comment_id}")
    public Comment getCommentById(@PathVariable Long comment_id){
        Comment comment = this.commentService.getCommentById(comment_id);
        return comment;
    }

    @DeleteMapping("/delete_comment_by_id/{comment_id}")
    public String deleteCommentById(@PathVariable Long comment_id){
        return this.commentService.deleteCommentById(comment_id);
    }

    @PutMapping("update_comment_by_id/{comment_id}")
    public Comment updateComment(@RequestBody CommentDto commentDto, @PathVariable Long comment_id){
        Comment updatedComment = this.commentService.updateCommentById(commentDto, comment_id);
        return updatedComment;
    }
}
