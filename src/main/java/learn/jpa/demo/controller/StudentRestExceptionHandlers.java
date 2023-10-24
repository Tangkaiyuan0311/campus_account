package learn.jpa.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandlers {
    // ResponseEntity represents an HTTP response, including headers, body, and status.
    // While @ResponseBody puts the return value into the body of the response, ResponseEntity also allows us to add headers and status codes.
    @ExceptionHandler // catch student not found
    public ResponseEntity<ErrorResponse> handleException(StudentNotFoundException exc) {
        // create an error response
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setErrMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // return a wrapper
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // catch all other exceptions as bad request
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        // create an error response
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // return a wrapper
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
