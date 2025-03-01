package api.Roamly.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionDetails> handleUserAlreadyExistsException(ResponseStatusException ex) {
        ExceptionDetails errorDetails = ExceptionDetails.builder()
                .title("Something went wrong, verify message and status")
                .message(ex.getReason())
                .status(ex.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGenericException(Exception ex) {
        ExceptionDetails errorDetails = ExceptionDetails.builder()
                .title("Internal server error")
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<String> fieldsWithError = errors.stream().map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage()).collect(Collectors.toList());

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Verify invalid fields")
                .timestamp(LocalDateTime.now())
                .message(String.join(",", fieldsWithError))
                .fields(fieldsWithError)
                .status(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

}

