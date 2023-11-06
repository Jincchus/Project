package kr.co.lotteon.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.request.member.MemberLoginRequest;
import kr.co.lotteon.request.member.MemberRegisterRequest;
import kr.co.lotteon.request.member.MemberSellerRegisterRequest;
import kr.co.lotteon.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("memberInfo") MemberRegisterRequest memberInfo) {

        //NOTI: templates앞에 /제거하기
        return "member/register";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute MemberRegisterRequest dto, HttpServletRequest request) {
        //TODO: register필드 유효성 검사하여 에러발견시 커스텀 에러 정의 해서 뷰단의 메시지로 보이기
        //TODO: BindingResult로 에러처리하기
        log.info("[REGISTER MEMBER INFO] = {}", dto);
        Member rawMember = dto.toMember();
        rawMember.setRegip(request.getRemoteAddr());

        Member registeredMember = memberService.registerMember(rawMember);
        try {
            request.login(registeredMember.getUid(),registeredMember.getPass());
        } catch (ServletException e) {
            //TODO: error 처리 객체 만들기
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/registerSeller")
    public String registerSellerForm(@ModelAttribute("sellerInfo") MemberSellerRegisterRequest dto) {
        return "member/registerSeller";
    }

    @PostMapping("/registerSeller")
    public String registerSeller(@ModelAttribute("sellerInfo") MemberSellerRegisterRequest dto,HttpServletRequest request) {
        log.info("[REGISTER SELLER INFO] = {}", dto);
        Member rawMember = dto.toMember();
        rawMember.setRegip(request.getRemoteAddr());

        Member registeredMember = memberService.registerMember(rawMember);
        try {
            request.login(registeredMember.getUid(),registeredMember.getPass());
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("memberLoginInfo") MemberLoginRequest dto) {
        return "member/login";
    }
}
