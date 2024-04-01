package phoenix.partyquest.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.file.UploadFile;

public interface UploadFileRepository extends JpaRepository<UploadFile,String> {

}
