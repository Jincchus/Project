package kr.co.lotteon.controller.cs;

import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.service.cs.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){
        List<ArticleEntity> notices = articleService.getNotices();
        model.addAttribute("notices", notices);
        return "cs/notice/list";
    }

    @GetMapping("/view")
    public String view(){
        return "cs/notice/view";
    }

}
