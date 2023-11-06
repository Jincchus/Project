package kr.co.lotteon.api.controller.admin.cs;


import kr.co.lotteon.response.admin.cs.CsArticleCateAPIResponse;
import kr.co.lotteon.service.admin.cs.CsArticleAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/cs")
public class CsArticleAPIController {

    private final CsArticleAPIService csArticleAPIService;
    //카테고리명에 따른 menu1,menu2조회하기
    @GetMapping("/article/fullCate/{cate}")
    public CsArticleCateAPIResponse test(@PathVariable("cate") String cate){
        return csArticleAPIService.getArticleCate(cate);
    }

}
