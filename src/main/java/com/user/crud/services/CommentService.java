package com.user.crud.services;

import com.user.crud.dto.CommentDto;
import com.user.crud.model.BlogPost;
import com.user.crud.model.Comment;
import com.user.crud.model.User;
import com.user.crud.repository.BlogPostRepository;
import com.user.crud.repository.CommentRepository;
import com.user.crud.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    UserRepository userRepository;

    public Comment createComment(CommentDto commentDto){
        Optional<BlogPost> post = blogPostRepository.findById(commentDto.getPost());
        if(!post.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        Optional<User> user = userRepository.findById(commentDto.getUser());
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentDto, Comment.class);
        MultipartFile imageFile = commentDto.getImageFile();
        if(imageFile != null && !imageFile.isEmpty()){
            try {
                comment.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading image file");
            }
        }
        return this.commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        return comment;
    }

    public String deleteCommentById(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(!comment.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        this.commentRepository.deleteById(commentId);
        return "Comment with ID " + commentId + " Deleted successfully";
    }

    public Comment updateCommentById(CommentDto commentDto, Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Comment with ID " + commentId + " does not exist"));
        comment.setContent(commentDto.getContent());
        return commentRepository.save(comment);
    }
}
