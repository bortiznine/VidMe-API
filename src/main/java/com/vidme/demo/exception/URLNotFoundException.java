package com.vidme.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class URLNotFoundException extends RuntimeException {

    public URLNotFoundException(String message) {
        super(message);


    }
}
