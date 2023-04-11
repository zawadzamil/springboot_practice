package com.example.practice.controller;

import com.example.practice.dto.UserDTO;
import com.example.practice.model.User;
import com.example.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private  UserService userService;

    @PostMapping("/create")
  public User create (@RequestBody @Valid UserDTO userDTO){
        return userService.saveAndUpdate(userDTO);
    }

}
