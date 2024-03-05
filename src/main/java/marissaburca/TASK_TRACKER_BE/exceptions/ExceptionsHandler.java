package marissaburca.TASK_TRACKER_BE.exceptions;

import marissaburca.TASK_TRACKER_BE.payloads.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    //ERROR 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericError ( Exception exception ) {
        exception.printStackTrace();
        return new ErrorsDTO("SERVER ERROR, we will solve as soon as possible", LocalDateTime.now());
    }

    //ERROR 404
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound ( NotFound exception ) {
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }

    //ERROR 401
    @ExceptionHandler(Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorized ( Unauthorized exception ) {
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }

    //ERROR 400
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest ( BadRequest exception ) {
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }
}
