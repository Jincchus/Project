package kr.co.lotteon.request.member;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.member.MemberGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(of = {"uid","pass","name","gender","email","hp","zip","addr1","addr2"})
@NoArgsConstructor
public class MemberRegisterRequest {
    private String uid;
    private String pass;
    private String pass2;
    private String name;
    private String gender;
    private String email;
    private String hp;
    private String zip;
    private String addr1;
    private String addr2;

    public Member toMember() {
        return Member.builder()
                .uid(this.uid)
                .pass(this.pass)
                .name(this.name)
                .gender(MemberGender.valueOf(this.gender)) //LEARN: String -> ENUM 타입으로 변환
                .email(this.email)
                .hp(this.hp)
                .zip(this.zip)
                .addr1(this.addr1)
                .addr2(this.addr2)
                .build();
    }
}
