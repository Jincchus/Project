package phoenix.partyquest.api.response.study;

import lombok.Data;

@Data
public class UploadFileResponse {

    private String url;

    public UploadFileResponse(String url) {
        this.url = url;
    }
}
