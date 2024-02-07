package phoenix.partyquest.domain.file;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class UploadFile {
    @Id
    private String storedName;
    private String uploadName;


    @Builder
    public UploadFile(String storedName, String uploadName) {
        this.storedName = storedName;
        this.uploadName = uploadName;
    }

    public boolean isEmpty(){
        return uploadName == null || uploadName.isEmpty();
    }
}

