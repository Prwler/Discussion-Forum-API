package com.user.crud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForumDto {
    private Integer forumId;
    private String forumName;
    private String forumDescription;
}
