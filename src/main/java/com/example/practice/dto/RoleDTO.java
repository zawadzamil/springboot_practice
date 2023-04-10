package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO implements Serializable {
    @NotNull(message = "Alias is Required.")
    @Size(min = 3, max = 20)
    private String alias;

    @NotNull(message = "Permission String is Requires.")
    private String permissionString;
}
