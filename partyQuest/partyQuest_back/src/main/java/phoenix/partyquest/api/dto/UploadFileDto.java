package phoenix.partyquest.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.file.UploadFile;

@Getter @NoArgsConstructor
public class UploadFileDto {
    private String origFileName;
    private String storedFileName;
    public UploadFileDto(String origFileName, String storedFileName) {
        this.origFileName = origFileName;
        this.storedFileName = storedFileName;
    }

    public UploadFile toUploadFile() {
        return UploadFile.builder()
                .uploadName(origFileName)
                .storedName(storedFileName)
                .build();
    }

    public boolean isEmpty() {
        return origFileName == null || origFileName.isEmpty() || storedFileName == null || storedFileName.isEmpty();
    }
}
