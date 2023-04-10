package com.example.practice.payloads;


import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১৩/১২/১৯
 * Time: ৫:১৭ PM
 * Email: jbjoniyed7@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse<T> {

    private Token token;
    private T liteUserDTO;

}
