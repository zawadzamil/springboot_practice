package com.example.practice.payloads;
import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১৪/১২/১৯
 * Time: ৭:২২ PM
 * Email: jbjoniyed7@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomPrincipal {

    private long userId;
    private String username;
    private String fullName;


}
