package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {

    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Username is required")
    @Size(max = 30, message = "Username must between {min} to {max} characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 character long.")
    private String password;

    private String profileImage;
}
