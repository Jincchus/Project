package kr.co.lotteon.service.file;

import kr.co.lotteon.dto.UploadFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String path) {
        return String.format("%s%s", fileDir, path);
    }
    public UploadFileDto storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String origFileName = file.getOriginalFilename();
        String storeFileName = genStoreFileName(origFileName);
        file.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFileDto(origFileName, storeFileName);
    }

    public boolean deleteFile(String storedFileName) throws IOException {
        Path path = Paths.get(getFullPath(storedFileName));
        return Files.deleteIfExists(path);

    }
    private String genStoreFileName(String originalFilename) {
        String ext = getExtension(originalFilename);

        //uuid -> 스토어에 저장시 편하게
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private String getExtension(String originalFilename) {
        // 확장자추출
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }
}
