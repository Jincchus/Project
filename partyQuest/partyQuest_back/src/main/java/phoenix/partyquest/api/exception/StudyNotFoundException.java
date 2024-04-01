package phoenix.partyquest.api.exception;

public class StudyNotFoundException extends PartyQuestException {
    private static final String MESSAGE = "해당 스터디는 존재하지 않습니다.";

    public StudyNotFoundException() {
        super(MESSAGE);
    }

    public StudyNotFoundException(String message) {
        super(message);
    }

    public StudyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
