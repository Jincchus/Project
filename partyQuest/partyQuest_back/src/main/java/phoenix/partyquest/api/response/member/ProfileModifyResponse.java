package phoenix.partyquest.api.response.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.profile.MemberProfile;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfileModifyResponse {
    private String avatarURL;
    private String nickName;
    private String bio;
    private String preferredLocation;
    private Long preferredLocationId;
    private String mbti;
    private List<String> favoriteFields;
    private List<String> favoriteTechs;
    private String link1;
    private String link2;

    public ProfileModifyResponse(MemberProfile memberProfile){
        this.avatarURL = memberProfile.getAvatar() == null? null: memberProfile.getAvatar().getStoredName();
        this.nickName = memberProfile.getNickName();
        this.bio = memberProfile.getBio();
        this.preferredLocation = memberProfile.getPreferredLocation().getLocationName();
        this.preferredLocationId = memberProfile.getPreferredLocation().getId();
        this.mbti = memberProfile.getMbti().name();
        this.favoriteFields = memberProfile.getFavoriteMiddles().stream().map(el->el.getMiddleCate().getName() + " "+el.getMiddleCate().getId()).collect(Collectors.toList());
        this.favoriteTechs = memberProfile.getFavoriteSmalls().stream().map(el->el.getSmallCate().getName() + " "+ el.getSmallCate().getId()).collect(Collectors.toList());
        this.link1 = memberProfile.getLink1();
        this.link2 = memberProfile.getLink2();
    }

}
