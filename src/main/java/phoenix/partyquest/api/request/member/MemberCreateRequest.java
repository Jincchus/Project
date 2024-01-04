package phoenix.partyquest.api.request.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.member.Member;

@Getter @Setter
@NoArgsConstructor
public class MemberCreateRequest {
    private String email;
    private String password;
    private String name;
    private String phone;

    /**
     * password는 service에서 PasswordEncoder를 이용하여 인코딩후 넣어준다.
     * @return
     */
    public Member.MemberBuilder toMemberBuilder() {
        return Member.builder()
                .email(this.email)
                .name(this.name)
                .phone(this.phone);
    }
}
