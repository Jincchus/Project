package phoenix.partyquest.api.response.community;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.community.Community;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityCreateResponse {
    private Long communityId;
    private Long writerId; // 로그인 구현 후 확인

    public CommunityCreateResponse(Community community){
        this.communityId = community.getId();
        this.writerId = community.getMember().getId();
    }
}
