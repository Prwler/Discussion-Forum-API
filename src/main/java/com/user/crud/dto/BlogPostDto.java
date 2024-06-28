package com.user.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class BlogPostDto {
    private Integer PostId;
    private String postTitle;
    private String postContent;
    @JsonIgnore
    private MultipartFile imageFile;
    private Long forumId;
    private Long userId;
}
