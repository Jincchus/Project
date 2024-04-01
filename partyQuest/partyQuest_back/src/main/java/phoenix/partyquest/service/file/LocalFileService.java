package phoenix.partyquest.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.dto.UploadFileDto;
import phoenix.partyquest.api.exception.FileDoesNotExist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Profile("local")
@Slf4j
@Service
public class LocalFileService implements FileService {
    @Value("${file.dir}")
    private String fileDir;
    @Override
    public String getFullPath(String path) {
        return String.format("%s%s", fileDir, path);
    }
    @Override
    public String getFullPathWithDir(String dir, String path) {
        String fullPath = String.format("%s%s%s", fileDir, dir + File.separator, path);

        // Replace backslashes with forward slashes in the URL
        fullPath = fullPath.replace("\\\\", "\\");

        return fullPath;
    }
    public UploadFileDto storeFile(MultipartFile file) throws IOException {
        if (file ==null ||file.isEmpty()) {
            // NullPointerException 방지로 null값 대신 파일이 비었으면 빈 UploadFileDto 반환
            return new UploadFileDto("","");
        }
        String origFileName = file.getOriginalFilename();

        if(origFileName == null) { origFileName = "" ;} // 원본 파일 이름이 null이면 빈 문자열로 처리

        String storeFileName = getStoreFileName(origFileName);
        file.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFileDto(origFileName, storeFileName);
    }
    @Override
    public UploadFileDto storeFile(MultipartFile file, String directory) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        createDirectoryIfNotExists(directory);
        String origFileName = file.getOriginalFilename();
        String storeFileName = getStoreFileName(origFileName);
        file.transferTo(new File(getFullPathWithDir(directory,storeFileName)));

        return new UploadFileDto(origFileName, storeFileName);
    }
    @Override
    @Async
    public void deleteFile(String storedFileName, String directory) {
        Path path = Paths.get(getFullPath(directory + File.separator + storedFileName));
        try {
             Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new FileDoesNotExist();
        }

    }
    @Override
    public String getStoreFileName(String originalFilename) {
        String ext = getExtension(originalFilename);

        //uuid -> 스토어에 저장시 편하게
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }
    @Override
    public String getExtension(String originalFilename) {
        // 확장자추출
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }
    // 디렉터리가 없는 경우 생성
    @Override
    public void createDirectoryIfNotExists(String directory) {
        File dir = new File(getFullPath(directory));
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
