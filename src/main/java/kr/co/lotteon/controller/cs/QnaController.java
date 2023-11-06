package kr.co.lotteon.controller.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.controller.dto.ArticleDTO;
import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticleCommentResponse;
import kr.co.lotteon.response.admin.cs.CsArticlePageResponseDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
import kr.co.lotteon.service.admin.cs.CsArticleService;
import kr.co.lotteon.service.cs.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {

    private final ArticleService articleService;

    private final CsArticleService csArticleService;


    @GetMapping(value = "/list")
    public String list(Model model, CsArticlePageRequestDTO pageRequestDTO ){
        pageRequestDTO.setCate("qna");
        log.info("menu1 : "+pageRequestDTO.getMenu1());
        log.info("menu2 : "+pageRequestDTO.getMenu2());
        CsArticlePageResponseDTO pageResponseDTO = csArticleService.findByCate(pageRequestDTO);
        log.info("pageResponseDTO :"+pageResponseDTO.toString());
        model.addAttribute("pageResponseDTO",pageResponseDTO);
        model.addAttribute("menu1",pageRequestDTO.getMenu1());
        model.addAttribute("menu2",pageRequestDTO.getMenu2());
        return "cs/qna/list";
    }

    @GetMapping("/view")
    public String view(@RequestParam("articleId") int articleId, Model model){
        log.info("qna view articleId:"+articleId);
        log.info("qna view articleId:"+articleId);
        CsArticleResponseDTO responseDTO   =  csArticleService.findById(articleId);
        log.info("qna view responseDTO:"+responseDTO.toString());
        if(responseDTO.getStatus().equals("답변완료")){
            log.info("답변완료 아티클 선택");
            CsArticleCommentResponse commentResponse = csArticleService.selectComment(articleId);
            log.info("답변출력 :"+commentResponse.getContent());
            model.addAttribute("commentResponse", commentResponse);
        }

        model.addAttribute("responseDTO",responseDTO);
        return "cs/qna/view";
    }

    @GetMapping("/write")
    public String writeView(){
        return "cs/qna/write";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request, ArticleDTO dto){
        dto.setRegip(request.getRemoteAddr());
        log.info("qna write : " + dto.toString());
        articleService.save(dto);
        return "redirect:/qna/list";
    }
}
