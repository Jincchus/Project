package phoenix.partyquest.api.response.community;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.community.CommunityCate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommunityListResponse {
    private Long communityId;
    private Long cateId;
    private String cateName;
    private String title;
    private String Content;
    private String writer;
    private String status;
    private LocalDateTime rdate;

    public CommunityListResponse(Community community) {
        this.communityId = community.getId();
        this.cateId = community.getCate().getId();
        this.cateName = community.getCate().getCateName();
        this.title = community.getTitle();
        this.Content = community.getContent();
        this.writer = community.getMember().getProfile().getNickName();
        this.status = String.valueOf(community.getStatus());
        this.rdate = community.getRdate();
    }
}
