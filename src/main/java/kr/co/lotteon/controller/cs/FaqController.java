package kr.co.lotteon.controller.cs;

import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.service.cs.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){
        List<ArticleEntity> faqList = articleService.getFaq();
        model.addAttribute("faqList", faqList);
        return "cs/faq/list";
    }

    @GetMapping("/view")
    public String view(){
        return "cs/faq/view";
    }


}
