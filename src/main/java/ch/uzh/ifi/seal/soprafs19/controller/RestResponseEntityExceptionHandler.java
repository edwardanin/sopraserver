package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.errorHandler.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(RegisterError.class)
    public ResponseEntity<HttpStatus> handleRegisterError() {
        return new ResponseEntity<>(HttpStatus.CONFLICT); //Code 409
    }

    @ExceptionHandler(RegisterSuccess.class)
    public ResponseEntity<HttpStatus> handleRegisterSuccess() {
        return new ResponseEntity<>(HttpStatus.CREATED); //Code 201
    }

    @ExceptionHandler(LoginUError.class)
    public ResponseEntity<HttpStatus> handleLoginUError() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Code 404
    }

    @ExceptionHandler(LoginPError.class)
    public ResponseEntity<HttpStatus> handleLoginPError() {
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //Code 406
    }

    @ExceptionHandler(UpdateUsernameError.class)
    public ResponseEntity<HttpStatus> handleUpdateUsernameError() {
        return new ResponseEntity<>((HttpStatus.CONFLICT)); //Code 409
    }
}
