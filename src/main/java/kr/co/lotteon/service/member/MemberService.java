package kr.co.lotteon.service.member;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member registerMember(Member preMember) {
        preMember.setPass(passwordEncoder.encode(preMember.getPass()));
        return memberRepository.save(preMember);
    }

    //member조회(point조회 및 주소를 가져오기 위해서 필요함)
    public Member findMember(Member uid){
        String userid = uid.getUid();
        Optional<Member> member = memberRepository.findById(userid);
        return member.get();
    }

    public Member findById(String uid){

        Member member = memberRepository.findById(uid).orElseThrow();
        return member;
    }
}
