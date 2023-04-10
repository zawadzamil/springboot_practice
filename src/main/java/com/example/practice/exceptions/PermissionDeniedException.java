package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১৪/১২/১৯
 * Time: ৭:১৪ PM
 * Email: jbjoniyed7@gmail.com
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(String s) {
        super(s);
    }
}
