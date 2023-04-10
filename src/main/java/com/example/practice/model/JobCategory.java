package com.example.practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name must not be empty")
    @Column(unique = true)
    private String name;

    @NotNull(message = "Slug is required")
    @Column(unique = true)
    private String slug;

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setSlug(String slug) {
        this.slug = slug.trim().toLowerCase();
    }
}
