package com.example.practice.service;

import com.example.practice.dto.UserDTO;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Job;
import com.example.practice.model.User;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User create(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return userRepository.save(user);
    }

    public Page<User> getAll (int id, PageRequest pageRequest){
        return userRepository.findAll(pageRequest);
    }

    public User update (int id, User user){

        return userRepository.findById(id).map(item -> {
            item.setName(user.getName());
            item.setUsername(user.getUsername());
            item.setPassword(user.getPassword());
            item.setProfileImage(user.getProfileImage());

            return userRepository.save(item);
        }).orElseThrow(()-> new ResourceNotFoundException("User not found."));
    }

    public String delete(int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        userRepository.deleteById(id);

        return "User deleted successfully.";
    }
}
