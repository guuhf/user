package com.guuh.user.infraestructure.handler;

import com.guuh.user.infraestructure.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyExistsHandler(UserAlreadyExistsException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity<RestErrorMessage> AddressNotFoundHandler(AddressNotFoundException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    private ResponseEntity<RestErrorMessage> PhoneNotFoundHandler(PhoneNotFoundException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<RestErrorMessage> AccessDeniedHandler(AccessDeniedException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.FORBIDDEN, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(threatResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestErrorMessage> InvalidCredentialsHandler(BadCredentialsException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED,"Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<RestErrorMessage> UsernameNotFoundHandler(UsernameNotFoundException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED,"Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(DuplicateUserPhoneException.class)
    private ResponseEntity<RestErrorMessage> DuplicateUserPhoneHanlder(DuplicateUserPhoneException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT,e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(DuplicateUserAddressException.class)
    private ResponseEntity<RestErrorMessage> DuplicateUserAddressHandler(DuplicateUserAddressException e){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT,e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }
}
