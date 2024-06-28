package com.user.crud.services;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.user.crud.dto.UserDto;
import com.user.crud.model.User;
import com.user.crud.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(UserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId);
        }
        return user;
    }

    public String deleteUserById(Long userId) {
        Optional<User> userbyId = userRepository.findById(userId);
        if(!userbyId.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + "does not exist");
        }
        this.userRepository.deleteById(userId);
        return  "User with ID " + userId + "Deleted successfully";
    }

    public User updateUserById(UserDto userDto, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User with ID " + userId + " does not exist"));

        // Update the user fields from the userDto, but don't update the id
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        return userRepository.save(existingUser);
    }
}
