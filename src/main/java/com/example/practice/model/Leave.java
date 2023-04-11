package com.example.practice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinTable(name = "user_leave",joinColumns = @JoinColumn(name = "leave_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private JobCategory category;
}
