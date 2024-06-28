package com.user.crud.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.crud.dto.ForumDto;
import com.user.crud.model.Forum;
import com.user.crud.repository.ForumRepository;

@Service
public class ForumService {
    @Autowired
    ForumRepository forumRepository;

    public Forum createForum(ForumDto forumDto){
        ModelMapper modelMapper = new ModelMapper();
        Forum forum = modelMapper.map(forumDto, Forum.class);
        return this.forumRepository.save(forum);
    }
}
