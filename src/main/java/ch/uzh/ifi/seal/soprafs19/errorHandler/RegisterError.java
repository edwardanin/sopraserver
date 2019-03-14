package ch.uzh.ifi.seal.soprafs19.errorHandler;

import org.springframework.http.HttpStatus;

public class RegisterError extends RuntimeException {
    public RegisterError() {
        super();
    }
}
