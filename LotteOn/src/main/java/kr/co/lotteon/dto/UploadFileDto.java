package kr.co.lotteon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UploadFileDto {
    private String origFileName;
    private String storedFileName;

    public UploadFileDto(String origFileName, String storedFileName) {
        this.origFileName = origFileName;
        this.storedFileName = storedFileName;
    }
}
