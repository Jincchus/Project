package phoenix.partyquest.api.response.member.profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.map.MiddleMemberMap;
import phoenix.partyquest.domain.member.profile.MemberProfile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberProfileViewResp {
    private String nickName;
    private String bio;
    private String mbti;
    private String preferredLocation;
    private List<String> favoriteMiddles = new ArrayList<>();
    private List<String> favoriteSmalls = new ArrayList<>();
    private String link1;
    private String link2;
    public MemberProfileViewResp(MemberProfile memberProfile){
        this.nickName =memberProfile.getNickName();
        this.bio =memberProfile.getBio();
        this.mbti =memberProfile.getMbti().name();
        this.preferredLocation =memberProfile.getPreferredLocation().getLocationName();
        //this.favoriteMiddles =memberProfile.getFavoriteMiddles();
        //this.favoriteSmalls =memberProfile.getFavoriteSmalls();
        this.link1 =memberProfile.getLink1();
        this.link2 =memberProfile.getLink2();
    }
    @Data
    static class favoriteMiddleDto{
        private String value;

        public favoriteMiddleDto(MiddleMemberMap memberMap) {
            this.value = value;
        }
    }
}
