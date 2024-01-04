package phoenix.partyquest.service.file;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
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
import java.util.UUID;

@Profile("preprod | s3test")
@Service
@Slf4j
@RequiredArgsConstructor
public class S3FileService implements FileService{
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
    @Override
    public UploadFileDto storeFile(MultipartFile file, String directory) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String origFileName = file.getOriginalFilename();
        String storeFileName = getStoreFileName(origFileName);
        String storeFilePath = getFullPathWithDir(directory,storeFileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, storeFilePath, file.getInputStream(), metadata);

        return new UploadFileDto(origFileName, storeFilePath);
    }
    @Override
    public void createDirectoryIfNotExists(String directory) {
    }
    @Override
    public String getFullPath(String path) {
        return null;
    }
    @Override
    public String getFullPathWithDir(String dir, String path) {
        String fullPath = String.format("%s%s", dir + File.separator, path);

        // Replace backslashes with forward slashes in the URL
        return fullPath;
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
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

    @Override
    @Async
    public void deleteFile(String storedFileName, String directory){
        String keyName = storedFileName;
        boolean doesObjectExist = amazonS3.doesObjectExist(bucket, keyName);
        log.info("[delete file service called] doesObjectExist = {}",doesObjectExist);
        try {
            if (doesObjectExist) {
                log.info("[delete file service called] doesObjectExist!!!");
                final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();
                s3.deleteObject(bucket, keyName);
            }
        } catch (Exception e) {
            throw new FileDoesNotExist("s3 파일 삭제 실패",e);
        }
        log.info("[delete file service called] completed");
    }
}
