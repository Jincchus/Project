package phoenix.partyquest.service.file;

import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.dto.UploadFileDto;

import java.io.IOException;

public interface FileService {
    public String getFullPath(String path);

    public String getFullPathWithDir(String dir, String path);

    public UploadFileDto storeFile(MultipartFile file, String directory) throws IOException;

    public String getStoreFileName(String originalFilename);

    public String getExtension(String originalFilename);

    public void createDirectoryIfNotExists(String directory);

    public void deleteFile(String storedFileName, String directory);
}
