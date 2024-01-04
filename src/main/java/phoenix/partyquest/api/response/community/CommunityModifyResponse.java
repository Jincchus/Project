package phoenix.partyquest.api.response.community;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import phoenix.partyquest.domain.community.Community;

import java.time.LocalDateTime;

@ToString @Getter @NoArgsConstructor
public class CommunityModifyResponse {
    private Long communityId;
    private Long writerId;
    private String file;
    private String title;
    private String content;
    private Long cateId;
    private LocalDateTime rdate;

    public CommunityModifyResponse(Community community) {
        this.communityId = community.getId();
        this.writerId = community.getMember().getId();
        this.file = community.getFile()==null?null:community.getFile().getUploadName();
        this.title = community.getTitle();
        this.content = community.getContent();
        this.cateId = community.getCate().getId();
        this.rdate = community.getRdate();
    }
}
