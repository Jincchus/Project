package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.partyquest.constants.FileDirectoryConstants;
import phoenix.partyquest.service.file.FileService;

@Slf4j
@Profile("local | default")
@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class DevResourceController {
    @Value("${file.dir}")
    private String fileDir;
    private final FileService fileService;

    @GetMapping("/images/{storedName}")
    public ResponseEntity<Resource> getProuctImage(@PathVariable("storedName") String storedName) {
        String fullPathWithDir = fileService.getFullPathWithDir(FileDirectoryConstants.THUMB, storedName);
        FileSystemResource imgResource = new FileSystemResource(fullPathWithDir);
        log.info("이거사용중");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "image/png");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/images/avatar/{storedName}")
    public ResponseEntity<Resource> getAvatarImage(@PathVariable("storedName") String storedName) {
        String fullPathWithDir = fileService.getFullPathWithDir(FileDirectoryConstants.MEMBER_AVATAR, storedName);
        FileSystemResource imgResource = new FileSystemResource(fullPathWithDir);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "image/png");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/images/community/{storedName}")
    public ResponseEntity<Resource> getCommunityImage(@PathVariable("storedName") String storedName) {
        String fullPathWithDir = fileService.getFullPathWithDir(FileDirectoryConstants.COMMUNITYFILE, storedName);
        log.info("[PATH] " + fullPathWithDir);
        FileSystemResource imgResource = new FileSystemResource(fullPathWithDir);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "image/png");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }
}
