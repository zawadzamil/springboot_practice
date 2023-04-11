package com.example.practice.service;

import com.example.practice.dto.UserDTO;
import com.example.practice.model.User;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User saveAndUpdate(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return userRepository.save(user);
    }
}
