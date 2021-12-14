package com.niit.ToDo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "user already exist")
public class UserAlreadyExistsException extends Exception {
}