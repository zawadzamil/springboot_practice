package com.example.practice.dto;

import com.example.practice.model.BlogCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDTO implements Serializable {
    @NotNull(message = "Title is required.")
    private String title;

    @NotNull(message = "Content is required.")
    private String content;

    @NotNull(message = "Slug is required.")
    private String slug;

    @ElementCollection(targetClass = String.class)
    private List<String> tags;

    private int category;

}
