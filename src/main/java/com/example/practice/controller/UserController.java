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
        try {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "User created successfully.",
                    "data", userService.create(userDTO)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "message", "Username already exists."
            ));
        }
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
        try {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Profile updated successfully",
                    "data", userService.update(id, user)
            ));
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message", e.getMessage()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                        "message", "Username must be unique"
                ));
            }
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", userService.delete(id)
        ));
    }
}
