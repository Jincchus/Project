package phoenix.partyquest.api.request.community;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommunityDeleteRequest {
    private Long writeId;
    private Long communityId;

}
