package az.ingress.bankapp.models.enums.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {

    String getKey();
    String getMessage();
    HttpStatus getHttpStatus();
}
