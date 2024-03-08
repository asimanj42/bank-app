package az.ingress.bankapp.exception.type;

import az.ingress.bankapp.models.enums.response.ResponseMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final ResponseMessage responseMessage;

    public static BaseException of(ResponseMessage responseMessage) {
        return BaseException.builder()
                .responseMessage(responseMessage)
                .build();
    }

    @Override
    public String getMessage() {
        return responseMessage.getMessage();
    }
}
