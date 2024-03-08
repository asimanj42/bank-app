package az.ingress.bankapp.exception.handler;

import az.ingress.bankapp.exception.error.ErrorResponse;
import az.ingress.bankapp.exception.type.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex,
                                                             HttpServletRequest request) {
        log.error("Exception occurred: ", ex);
        return getErrorResponse(ex, request);
    }

    private static ResponseEntity<ErrorResponse> getErrorResponse(BaseException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(ex.getResponseMessage().getHttpStatus())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), ex.getResponseMessage().getHttpStatus());
    }


}
