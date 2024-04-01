package phoenix.partyquest.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class MyErrorResponse {
    private final String code;
    private final String errorMessage;
    //TODO: 맵 안쓰는 방식으로 바꾸어보자
    private final Map<String, String> validation;

    public void addValidation(String field, String message) {
        validation.put(field, message);
    }
    @Builder
    public MyErrorResponse(String code, String errorMessage,Map<String,String> validation) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.validation = validation;
    }
}
