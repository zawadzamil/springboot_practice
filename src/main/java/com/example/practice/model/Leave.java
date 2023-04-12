package com.example.practice.model;

import com.example.practice.enums.LeaveNature;
import com.example.practice.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Leave Nature is required")
    @Enumerated(EnumType.STRING)
    private LeaveNature natureOfLeave;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Size(max = 500, message = "Reason up to 500 characters")
    private String reason;

    @NotNull
    private Date dateOfJoining;

    private Date dateOfActualJoining;

    private int totalLeaveAvailed;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinTable(name = "leave_user",joinColumns = @JoinColumn(name = "leave_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;


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
