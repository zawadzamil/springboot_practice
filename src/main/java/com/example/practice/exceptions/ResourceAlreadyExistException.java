package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ১০/১২/১৯
 * Time: ১:১৯ PM
 * Email: jbjoniyed7@gmail.com
 */


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException(String message) {
        super(message);
    }

}
