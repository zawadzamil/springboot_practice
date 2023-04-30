package com.example.practice.service;

import com.example.practice.dto.UserDTO;
import com.example.practice.exceptions.ResourceAlreadyExistException;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.User;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(UserDTO userDTO) {
        User existing = userRepository.findByUsername(userDTO.getUsername());

        if (existing != null) {
            throw new ResourceAlreadyExistException("Username already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return userRepository.save(user);
    }

    public Page<User> getAll(int id, PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public User update(int id, User user) {
        User existing = userRepository.findByUsername(user.getUsername());

        if(existing != null && existing.getId() != id) {
            throw new ResourceAlreadyExistException("Username already exists");
        }

        return userRepository.findById(id).map(item -> {
            item.setName(user.getName());
            item.setUsername(user.getUsername());
            item.setPassword(user.getPassword());
            item.setProfileImage(user.getProfileImage());

            return userRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    public String delete(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(id);

        return "User deleted successfully.";
    }
}
