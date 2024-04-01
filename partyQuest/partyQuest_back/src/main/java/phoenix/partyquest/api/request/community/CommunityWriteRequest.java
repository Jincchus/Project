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
public class CommunityWriteRequest {
    private Long writerId;
    private MultipartFile file;
    private String title;
    private String content;
    private Long cateId;
    private LocalDateTime rdate;

    public Community.CommunityBuilder toCommunityBuilder(CommunityCate cate, Member writer){
        Community.CommunityBuilder communityBuilder = Community.builder();
        communityBuilder = communityBuilder
                .cate(cate)
                .writer(writer)

                .title(this.title)
                .content(this.content)
                .rdate(LocalDateTime.now());
        return communityBuilder;

    }

}
