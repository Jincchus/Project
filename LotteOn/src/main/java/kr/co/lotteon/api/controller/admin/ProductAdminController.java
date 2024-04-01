package kr.co.lotteon.api.controller.admin;

import kr.co.lotteon.response.admin.product.Cate1DictAPIResponse;
import kr.co.lotteon.service.admin.product.CateAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
public class ProductAdminController {
    //카테고리 1차,2차 값 가져오기
    private final CateAPIService cateAPIService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/fullCate")
    public List<Cate1DictAPIResponse> test() {
        return cateAPIService.getCateDictResponse();
    }

    /**
     * product 이미지를 요청 받으면 이미지를 준다.
     */
    @GetMapping("/images/{storedName}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("storedName") String storedName) throws IOException {
        String imgPathStr = String.format("%s%s", fileDir, storedName);
        FileSystemResource imgResource = new FileSystemResource(imgPathStr);
        HttpHeaders httpHeaders = new HttpHeaders();
  
        //content-type 등록
        //Path imgPath = Paths.get(imgPathStr);
        //String contentType = Files.probeContentType(imgPath);
        httpHeaders.add("Content-Type", "image/jpeg");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }
}
