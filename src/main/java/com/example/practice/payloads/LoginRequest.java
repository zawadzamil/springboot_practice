package com.example.practice.payloads;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১৩/১২/১৯
 * Time: ৩:০০ PM
 * Email: jbjoniyed7@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password"})
public class LoginRequest {

    private String username;

    @NotNull(message = "Password must not be null")
    private String password;

    @Pattern(regexp = "^\\+88(01)([123456789])(\\d{8})", message = "Must be a valid phone number.")
    private String primaryPhone;

    private double latitude;
    private double longitude;
    private String deviceName;
    private String deviceModel;
    private String operatingSystemVersion;
    private int sdkVersion;
    private String deviceType;
    private String macAddress;
    private String idAddress;

}
