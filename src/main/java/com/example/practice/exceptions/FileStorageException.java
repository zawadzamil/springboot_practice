package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/১২/১৯
 * Time: ১০:৪৫ AM
 * Email: jbjoniyed7@gmail.com
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileStorageException extends RuntimeException {

    public FileStorageException(String s) {
        super(s);
    }
}
