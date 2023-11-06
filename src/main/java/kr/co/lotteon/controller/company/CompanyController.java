package kr.co.lotteon.controller.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {
    @GetMapping("/index")
    public String index() {

        return "company/index";
    }
    @GetMapping("/introduce")
    public String introduce() {

        return "company/introduce";
    }
    @GetMapping("/manage")
    public String manage() {

        return "company/manage";
    }
    @GetMapping("/notice")
    public String notice() {

        return "company/notice";
    }
    @GetMapping("/promote")
    public String promote() {
        return "company/promote";
    }
}
