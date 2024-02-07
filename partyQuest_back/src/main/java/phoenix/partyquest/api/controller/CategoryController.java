package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.partyquest.service.category.CategoryService;
import phoenix.partyquest.service.category.MajorAPIService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //동한
    @GetMapping("/cached/allCate")
    public List<MajorAPIService> getCachedAllCate() {
        log.info("cached controller called");
        return categoryService.allCates();
    }
    //동한 끝
    //경진
    //경진 끝
    //현정
    //현정 끝
    //동일

    @GetMapping("/allCate")
    public List<MajorAPIService> allCate() {
            return categoryService.allCates();
    }

    //동일 끝

}
