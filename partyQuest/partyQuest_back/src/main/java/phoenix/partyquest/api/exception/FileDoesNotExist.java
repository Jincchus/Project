package phoenix.partyquest.api.exception;

public class FileDoesNotExist extends PartyQuestException{
    private static final String MESSAGE = "파일이 존재하지 않습니다.";

    public FileDoesNotExist() {
        super(MESSAGE);
    }

    public FileDoesNotExist(String message) {
        super(message);
    }

    public FileDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
