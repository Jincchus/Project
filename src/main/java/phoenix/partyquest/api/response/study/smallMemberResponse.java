package phoenix.partyquest.api.response.study;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import phoenix.partyquest.domain.member.map.SmallMemberMap;

@ToString
@Getter
@NoArgsConstructor
public class smallMemberResponse {
    private Long memberId;
    private Long smallCateId;
    private String smallCateName;

    public smallMemberResponse(SmallMemberMap smallMember) {
        this.memberId = smallMember.getProfile().getId();
        this.smallCateId = smallMember.getSmallCate().getId();
        this.smallCateName = smallMember.getSmallCate().getName();
    }
}
