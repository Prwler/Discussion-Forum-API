package com.user.crud.services;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import com.user.crud.dto.BlogPostDto;
import com.user.crud.model.BlogPost;
import com.user.crud.model.Forum;
import com.user.crud.model.User;
import com.user.crud.repository.BlogPostRepository;
import com.user.crud.repository.ForumRepository;
import com.user.crud.repository.UserRepository;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForumRepository forumRepository;

    public BlogPost createBlogPost(BlogPostDto blogPostDto){
        Optional<User> user = userRepository.findById(blogPostDto.getUserId());
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Optional<Forum> forum = forumRepository.findById(blogPostDto.getForumId());
        if(!forum.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        BlogPost blogPost = modelMapper.map(blogPostDto, BlogPost.class);

        MultipartFile imageFile = blogPostDto.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                blogPost.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading image file");
            }
        }
        return this.blogPostRepository.save(blogPost);
    }

    public String deletePostById(Long postId) {
        Optional<BlogPost> postById = blogPostRepository.findById(postId);
        if(!postById.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        this.blogPostRepository.deleteById(postId);
        return "Post with ID " + postId + "Deleted successfully";
    }

    public BlogPost updatePostById(BlogPostDto blogPostDto, Long postId) {
        BlogPost existingPost = blogPostRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Post with ID " + postId + " does not exist"));
        existingPost.setPostTitle(blogPostDto.getPostTitle());
        existingPost.setPostContent(blogPostDto.getPostContent());
        return blogPostRepository.save(existingPost);
    }
}
