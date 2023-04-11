package com.example.practice.model;

import com.example.practice.enums.JobNature;
import com.example.practice.enums.JobType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

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
    private JobType type ;

    @NotNull(message = "Slug is required")
    @Column(unique = true)
    private String slug;

    @NotNull(message = "Job Nature is required")
    @Enumerated(EnumType.STRING)
    private JobNature nature;


    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinTable(name = "job_job_category",joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "jobcategory_id"))
    private JobCategory category;

    private String description;

    private Date deadline;

    private int noOfVacancies;

    private String experience;

    private boolean active = false;
    @JsonProperty(access = READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(access = READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
