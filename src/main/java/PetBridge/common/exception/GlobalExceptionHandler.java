package PetBridge.common.exception;

import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.common.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdoptionPostNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAdoptionPostNotFoundException(
            AdoptionPostNotFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}