package kr.co.lotteon.controller.cs;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsMainController {

    @GetMapping("/cs/index")
    public String cs_index(){
        return "cs/index";
    }

}
