package com.example.practice.dto;

import com.example.practice.enums.JobNature;
import com.example.practice.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO implements Serializable {
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Job Type is required")
    @Enumerated(EnumType.STRING)
    private JobType type ;

    @NotNull(message = "Slug is required")
    private String slug;

    @NotNull(message = "Job Nature is required")
    @Enumerated(EnumType.STRING)
    private JobNature nature;


    private int category;

    private String description;

    private Date deadline;

    private int noOfVacancies;

    private String experience;

}
