package kr.co.lotteon.request.member;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.member.MemberGender;
import kr.co.lotteon.entity.member.MemberLevel;
import kr.co.lotteon.entity.member.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberSellerRegisterRequest {
    private String uid;
    private String pass;
    private String pass2;
    private String name;
    private String hp;
    private String email;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String fax;

    public Member toMember() {
        return Member.builder()
                .uid(this.uid)
                .pass(this.pass)
                .name(this.name)
                .gender(MemberGender.SELLER)
                .role(MemberRole.ROLE_SELLER)
                .email(this.email)
                .hp(this.hp)
                .zip(this.zip)
                .addr1(this.addr1)
                .addr2(this.addr2)
                .level(MemberLevel.SELLER)
                .company(this.company)
                .ceo(this.ceo)
                .bizRegNum(this.bizRegNum)
                .comRegNum(this.comRegNum)
                .tel(this.tel)
                .fax(this.fax)
                .manager(this.name) // 일단 매니저랑 이름 같게 처리
                .managerHp(this.hp)
                .build();
    }
}
