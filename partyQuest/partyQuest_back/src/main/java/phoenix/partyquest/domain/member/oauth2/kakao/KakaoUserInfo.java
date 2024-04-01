package phoenix.partyquest.domain.member.oauth2.kakao;

import lombok.AllArgsConstructor;
import phoenix.partyquest.domain.member.oauth2.OAuth2UserInfo;

import java.util.Map;

@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        // kakao_account라는 Map에서 추출
        return (String) ((Map) attributes.get("kakao_account")).get("email");
    }

    @Override
    public String getName() {
        // kakao_account라는 Map에서 추출
        return (String) ((Map) attributes.get("properties")).get("nickname");
    }
}
