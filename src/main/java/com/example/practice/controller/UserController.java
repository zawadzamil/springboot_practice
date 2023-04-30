package com.example.practice.controller;

import com.example.practice.dto.UserDTO;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.User;
import com.example.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(userDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") int id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(id, pageRequest));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(@RequestParam("id") int id, @RequestBody() User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", userService.delete(id)
        ));
    }
}
