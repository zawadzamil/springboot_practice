package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১০/১২/১৯
 * Time: ১:২১ PM
 * Email: jbjoniyed7@gmail.com
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }

}
