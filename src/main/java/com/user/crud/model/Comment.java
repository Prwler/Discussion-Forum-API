package com.user.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name="content")
    private String content;

    @Lob
    @Column(name="comment_image", nullable = true)
    private byte[] Image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private BlogPost posts;
}
