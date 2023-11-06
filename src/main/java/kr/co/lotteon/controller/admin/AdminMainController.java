package kr.co.lotteon.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {

    @GetMapping(value = {"admin/","/admin/index"})
    public String admin_index(){
        return"admin/index";
    }

}
