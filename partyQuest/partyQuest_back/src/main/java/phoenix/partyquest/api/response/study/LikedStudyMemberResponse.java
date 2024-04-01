package phoenix.partyquest.api.response.study;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.party.study.map.LikedStudyMemberMap;

@Data
@NoArgsConstructor
public class LikedStudyMemberResponse {
    private Long memberId;

    @Builder
    public LikedStudyMemberResponse(LikedStudyMemberMap likedStudyMemberMap) {
        this.memberId = likedStudyMemberMap.getMember().getId();
    }
}
