package com.example.practice.controller;

import com.example.practice.dto.UserDTO;
import com.example.practice.model.User;
import com.example.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static UserService userService;

    @PostMapping("/create")
  public User create (@RequestBody UserDTO userDTO){
        return userService.saveAndUpdate(userDTO);
    }

}
