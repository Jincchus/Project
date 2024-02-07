package phoenix.partyquest.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class PartyQuestException extends RuntimeException{
    public final Map<String,String> validations = new HashMap<>();

    public PartyQuestException(String message) {
        super(message);
    }

    public PartyQuestException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatus();

    public void addValidations(String fieldName, String msg) {
        this.validations.put(fieldName, msg);
    }
}
