package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১০/১২/১৯
 * Time: ১:২৫ PM
 * Email: jbjoniyed7@gmail.com
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VerificationFailedException extends RuntimeException {

    public VerificationFailedException(String message) {
        super(message);
    }
}
