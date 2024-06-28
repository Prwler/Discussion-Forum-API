package com.user.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.crud.dto.ForumDto;
import com.user.crud.model.Forum;
import com.user.crud.services.ForumService;

@RestController
@RequestMapping("/api")
public class ForumController {
    @Autowired
    ForumService forumService;

    @PostMapping("/create_forum")
    public Forum addForum(@RequestBody ForumDto forumDto){
        return this.forumService.createForum(forumDto);
    }
}
