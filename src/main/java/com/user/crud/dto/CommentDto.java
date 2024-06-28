package com.user.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CommentDto {
    private Integer CommentId;
    private String content;
    @JsonIgnore
    private MultipartFile imageFile;
    private Long userId;
    private Long postId;
}

