package phoenix.partyquest.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import phoenix.partyquest.api.exception.PartyQuestException;
import phoenix.partyquest.api.response.MyErrorResponse;

import java.util.HashMap;

@RestControllerAdvice
public class StudyControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MyErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        MyErrorResponse response = MyErrorResponse.builder()
                .code("400")
                .errorMessage("잘못된 요청이오")
                .validation(new HashMap<>())
                .build();

        FieldError fieldError = e.getFieldError();
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();

        response.addValidation(field, message);
        return response;
    }

    @ExceptionHandler(PartyQuestException.class)
    public ResponseEntity<MyErrorResponse> noSuchHandler(PartyQuestException e) {
        MyErrorResponse errorResponse = MyErrorResponse.builder()
                .code(String.valueOf(e.getStatus()))
                .errorMessage(e.getMessage())
                .validation(e.getValidations())
                .build();

        return ResponseEntity.status(Integer.parseInt(errorResponse.getCode()))
                .body(errorResponse);
    }
}
