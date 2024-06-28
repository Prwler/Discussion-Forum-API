package com.user.crud.model;

import jakarta.persistence.Entity;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="forums")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer forumId;

    @Column(name="forum_name")
    private String forumName;

    @Column(name="forum_description")
    private String forumDescription;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
    private List<BlogPost> posts;
}
