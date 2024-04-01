package phoenix.partyquest.api.request.community;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import phoenix.partyquest.domain.community.Community;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.domain.member.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityModifyRequest {
    private Long communityId;
    private Long writerId;
    private MultipartFile file;
    private String title;
    private String content;
    private Long cateId;
    private LocalDateTime rdate;

    public Community updateCommunity(Community community){
        community.setTitle(this.title);
        community.setContent(this.content);
        community.setRdate(this.rdate);

        return community;
    }


}
