package com.example.practice.payloads;

import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ১০/৩/২০
 * Time: ৩:৪৪ PM
 * Email: jbjoniyed7@gmail.com
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Token {

    private String access;
    private String refresh;

}
