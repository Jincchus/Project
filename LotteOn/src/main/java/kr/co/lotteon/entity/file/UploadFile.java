package kr.co.lotteon.entity.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "km_file")
public class UploadFile {
    @Id @Column(name = "stored_name")
    private String storedFileName; // 파일 시스템에 저장된 이름 UUID기반이다.
    @Column(name = "upload_name")
    private String uploadFileName; // 유저가 올린 이름

    @Builder
    public UploadFile(String uploadFileName, String storedFileName) {
        this.uploadFileName = uploadFileName;
        this.storedFileName = storedFileName;
    }
}
