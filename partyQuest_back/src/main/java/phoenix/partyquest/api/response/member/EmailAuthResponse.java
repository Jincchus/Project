package phoenix.partyquest.api.response.member;

import lombok.Builder;
import lombok.Data;

@Data
public class EmailAuthResponse {

    private int countEmail;
    private String key;

    @Builder
    public EmailAuthResponse(int countEmail, String key) {
        this.countEmail = countEmail;
        this.key = key;
    }
}
