package phoenix.partyquest.api.response.community;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import phoenix.partyquest.domain.community.Community;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @Slf4j @ToString
public class CommunityViewResponse {
    private Long communityId;
    private String cateName;
    private String title;
    private String content;
    private String file;
    private String writer;
    private Long writeId;
    private String status;
    private LocalDateTime rdate;

    public CommunityViewResponse(Community community) {
        this.communityId = community.getId();
        this.cateName = community.getCate().getCateName();
        this.title = community.getTitle();
        this.content = community.getContent();
        this.file = community.getFile()==null? null : community.getFile().getStoredName();
        log.info(file);
        this.writer = community.getMember().getProfile().getNickName();
        this.writeId = community.getMember().getId();
        this.status = String.valueOf(community.getStatus());
        this.rdate = community.getRdate();
    }
}
