package PetBridge.common.exception;

import PetBridge.adoptionPost.exception.AdoptionPostNotFoundException;
import PetBridge.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdoptionPostNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleAdoptionPostNotFoundException(
            AdoptionPostNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}