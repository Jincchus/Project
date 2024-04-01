package phoenix.partyquest.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.api.request.member.MemberCreateRequest;
import phoenix.partyquest.api.request.member.MemberListRequest;
import phoenix.partyquest.api.request.member.MemberLoginRequest;
import phoenix.partyquest.api.request.member.ProfileModifyRequest;
import phoenix.partyquest.api.response.member.EmailAuthResponse;
import phoenix.partyquest.api.response.member.MemberListResponse;
import phoenix.partyquest.api.response.member.MemberMessageResponse;
import phoenix.partyquest.api.response.member.ProfileModifyResponse;
import phoenix.partyquest.api.response.study.PartyLocationResponse;
import phoenix.partyquest.constants.SecurityConstants;
import phoenix.partyquest.domain.member.profile.MemberMBTI;
import phoenix.partyquest.repository.party.location.PartyLocationRepository;
import phoenix.partyquest.service.email.EmailService;
import phoenix.partyquest.service.member.MemberService;
import phoenix.partyquest.service.study.StudyService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final EmailService emailService;
    private final StudyService studyService;
    private final PartyLocationRepository partyLocationRepository;

    //TODO: 쿠키로 처리
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest dto, HttpServletResponse response) {
        //TODO: 로그인 한 상태로 다시 로그인 했을때 처리하기
        log.info("[login] {}", dto.getEmail());
        String jwtToken = memberService.singInMemberAndIssueJWTToken(dto, response);
        HttpHeaders headers = new HttpHeaders();
        headers.set(SecurityConstants.JWT_HEADER, jwtToken);
        return ResponseEntity.ok()
                .headers(headers)
                .body(String.format("%s 로그인.", dto.getEmail()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody MemberCreateRequest dto, HttpServletResponse response) {
        log.info("[MemberCreateRequest dto]", dto);
        String jwtToken = memberService.signUpMemberAndGetJWTToken(dto, response);
        HttpHeaders headers = new HttpHeaders();
        headers.set(SecurityConstants.JWT_HEADER, jwtToken);
        return ResponseEntity.ok()
                .headers(headers)
                .body(String.format("%s 회원 가입이 완료 되었습니다.", dto.getName()));
    }

    @GetMapping("/sign-out")
    public ResponseEntity<String> signOut(Authentication authentication,HttpServletResponse response) {
        memberService.signOut(authentication,response);
        return ResponseEntity.ok(String.format("%s 로그 아웃 되었습니다.",authentication.getName()));
    }

    //동한
    @PostMapping("/profile/modify/avatar")
    public ResponseEntity<String> updateAvatar(Authentication authentication, @RequestParam("avatar") MultipartFile avatar) throws IOException {
        memberService.updateProfileAvatar(authentication, avatar);
        return ResponseEntity.ok("good");
    }
    @PostMapping("/profile/modify")
    public ResponseEntity modifyProfile(Authentication authentication,@RequestBody ProfileModifyRequest dto) {
        memberService.modifyProfile(authentication, dto);
        return ResponseEntity.status(200).build();
    }
    @GetMapping("/profile")
    public ResponseEntity<ProfileModifyResponse> getProfile(Authentication authentication) {
        return ResponseEntity.ok(memberService.getMemberProfile(authentication));
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping("/cached/locations")
    public ResponseEntity<List<PartyLocationResponse>> getLocations() {
        List<PartyLocationResponse> results = partyLocationRepository.findAll().stream()
                .map(PartyLocationResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping("/cached/mbtis")
    public ResponseEntity<List<String>> getMbtis() {
        List<String> results = Arrays.stream(MemberMBTI.values()).map(el -> el.name())
                .collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
    //동한 끝

    //경진

    //경진 끝


    //현정

    /**
     * 이메일 인증
     * @param email
     * @return
     */
    @PostMapping("/email-auth/{email}")
    public ResponseEntity<EmailAuthResponse> checkEmailAuth(@PathVariable("email") String email){
        if (email != null) {
            log.info("[EMAIL WENT THROUGH]: " + email);
            EmailAuthResponse response = null;
            try {
                int count = memberService.checkEmailDuplicate(email);


                if (count == 0) {
                    // 이메일 중복 X & 인증코드 전송
                    String key = emailService.sendSimpleMessage(email);

                    // 객체 저장
                    response = EmailAuthResponse.builder()
                            .countEmail(count)
                            .key(key)
                            .build();
                } else if(count == 1) {
                    // 이메일 중복
                    response = EmailAuthResponse.builder()
                            .countEmail(count)
                            .key(null)
                            .build();
                }
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                // 이 외의 에러 발생
                log.error("Error processing email authentication for: " + email, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * 소셜로그인 카카오
     * @param code
     */
    @GetMapping("/oauth/kakao")
    public void kakaoLogin(@RequestParam String code){
        log.info("code : "+ code);
    }

    /**
     * 멤버 목록 & 페이징 & 서치
     * @param memberListRequest
     * @return memberPagedList
     */
    @GetMapping("/list")
    public ResponseEntity<Page<MemberListResponse>> getMemberList(@ModelAttribute MemberListRequest memberListRequest){
        Page<MemberListResponse> memberPagedList = memberService.getMemberListWithCond(memberListRequest);
        log.info("[CONTROLLER members]"+ memberPagedList);
        return ResponseEntity.ok().body(memberPagedList);
    }
    
    
    //현정 끝

    //동일
    @GetMapping("/unreadMessage/{memberId}")
    public int findUnreadMessage(@PathVariable("memberId") String memberId){
        int result = studyService.findUnreadMessage(memberId);
        return result;
    }
    @GetMapping("findMessageList/{memberId}")
    public List<MemberMessageResponse> findMessageList(@PathVariable("memberId")String memberId){
        List<MemberMessageResponse> messageList = memberService.messageList(memberId);
        System.out.println("messageList :"+messageList.toString());
        return messageList;
    }
    //동일 끝
}