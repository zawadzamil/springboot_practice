package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১০/১২/১৯
 * Time: ১:২২ PM
 * Email: jbjoniyed7@gmail.com
 */


@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAuthenticationException extends RuntimeException {

    public InvalidAuthenticationException(String s) {
        super(s);
    }

}
