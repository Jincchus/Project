package phoenix.partyquest.api.request.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.profile.MemberMBTI;
import phoenix.partyquest.domain.member.profile.MemberProfile;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileModifyRequest {
    private String nickName;
    private String bio;
    private Long preferredLocationId;
    private String mbti;
    private List<Long> favoriteFields;
    private List<Long> favoriteTechs;
    private String link1;
    private String link2;

    public MemberProfile.MemberProfileBuilder toMemberProfile() {
        return MemberProfile.builder()
                .bio(bio)
                .link1(link1)
                .link2(link2)
                .mbti(MemberMBTI.valueOf(mbti))
                .nickName(nickName);
    }
}
