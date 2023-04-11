package com.example.practice.model;

import com.example.practice.enums.JobNature;
import com.example.practice.enums.JobType;
import com.example.practice.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Job Type is required")
    @Enumerated(EnumType.STRING)
    private JobType status ;

    @NotNull(message = "Slug is required")
    @Column(unique = true)
    private String slug;

    @NotNull(message = "Job Nature is required")
    @Enumerated(EnumType.STRING)
    private JobNature jobNature;


    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinTable(name = "job-and-category",joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "jobcategory_id"))
    private JobCategory category;

    private String description;

    private Date deadline;

    private int noOfVacancies;

    private String experience;

    private boolean active = false;

}
