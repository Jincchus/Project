package phoenix.partyquest.api.request.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberLoginRequest {
    private String email;
    private String password;
}
