package com.example.practice.dto;

import com.example.practice.enums.LeaveNature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaveDTO implements Serializable {
    private int user;

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

}
