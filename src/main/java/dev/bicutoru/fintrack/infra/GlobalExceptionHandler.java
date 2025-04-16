package dev.bicutoru.fintrack.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handler404() { return ResponseEntity.notFound().build(); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handler400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        errors.forEach(err -> System.out.println(err.getField() + ": " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorData::new).toList());
    }

    public record ErrorData(String field, String message) {
        public ErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}