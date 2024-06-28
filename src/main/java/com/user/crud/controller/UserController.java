package com.user.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.crud.dto.UserDto;
import com.user.crud.model.User;
import com.user.crud.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/create_user")
    public User addUser(@RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }

    @GetMapping("/get_all_users")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/get_user_by_id/{user_id}")
    public User getUserById(@PathVariable Long user_id){
        User user = this.userService.getUserById(user_id);
        return user;
    }

    @DeleteMapping("/delete_user_by_id/{user_id}")
    public String deleteUserById(@PathVariable Long user_id){
        return this.userService.deleteUserById(user_id);
    }

    @PutMapping("update_user_by_id/{user_id}")
    public User updateUser(@RequestBody UserDto userDto, @PathVariable Long user_id) {
        User updatedUser = this.userService.updateUserById(userDto, user_id);
        return updatedUser;
    }
}
