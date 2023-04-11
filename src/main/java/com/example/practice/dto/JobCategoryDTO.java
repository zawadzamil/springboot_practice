package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobCategoryDTO implements Serializable {
    @NotNull(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Slug is required")
    private String slug;
}
