package com.example.practice.payloads;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ৩১/৩/২০
 * Time: ১:৪২ PM
 * Email: jbjoniyed7@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChangePasswordRequest {

    @Size(min = 6, message = "Old password length at least {min} character")
    private String oldPassword;

    @NotNull(message = "New password must not be null")
    @Size(min = 6, message = "New password length at least {min} character")
    private String newPassword;

}
