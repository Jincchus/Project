package phoenix.partyquest.api.response.member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.category.SmallCate;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.MemberRole;
import phoenix.partyquest.domain.member.map.MiddleMemberMap;
import phoenix.partyquest.domain.member.map.SmallMemberMap;
import phoenix.partyquest.domain.member.profile.MemberMBTI;
import phoenix.partyquest.domain.party.location.PartyLocation;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberListResponse {

    private Long memberId;
    private String email;
    private MemberRole role;
    private String nickname;
    private MemberMBTI mbti;
    private String avatar;
    private PartyLocation preferredLocation;
    private List<MiddleCateIdNameResponse> middleCates;
    private List<SmallCateIdNameResponse> smallCates;

    public MemberListResponse(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.nickname = member.getProfile().getNickName();
        this.mbti = member.getProfile().getMbti();
        this.avatar = member.getProfile().getAvatar() != null ? member.getProfile().getAvatar().getStoredName() : null;
        this.preferredLocation = member.getProfile().getPreferredLocation();
        this.middleCates = member.getProfile().getFavoriteMiddles().stream()
                .map(MiddleCateIdNameResponse::new)
                .collect(Collectors.toList());
        this.smallCates = member.getProfile().getFavoriteSmalls().stream()
                .map(SmallCateIdNameResponse::new)
                .collect(Collectors.toList());
    }

    @Getter @NoArgsConstructor
    static class MiddleCateIdNameResponse {
        private Long id;
        private String name;

        public MiddleCateIdNameResponse(MiddleMemberMap middleMemberMap) {
            MiddleCate middleCate = middleMemberMap.getMiddleCate();
            this.id = middleCate.getId();
            this.name = middleCate.getName();
        }
    }
    @Getter @NoArgsConstructor
    static class SmallCateIdNameResponse {
        private Long id;
        private String name;

        public SmallCateIdNameResponse(SmallMemberMap smallMemberMap) {
            SmallCate smallCate = smallMemberMap.getSmallCate();
            this.id = smallCate.getId();
            this.name = smallCate.getName();
        }
    }

}
