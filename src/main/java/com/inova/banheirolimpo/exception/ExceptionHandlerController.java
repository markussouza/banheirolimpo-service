/**
 * 
 */
package com.inova.banheirolimpo.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Markus Souza on 06/02/2018
 * 
 * Essa controller será responsável por interceptar e tratar as exceções lançadas pela aplicação.
 *
 */

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validateError(ConstraintViolationException ex){
        return ResponseEntity.badRequest().body(ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> otherErrors(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
