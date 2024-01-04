package phoenix.partyquest.domain.member.oauth2;

import lombok.Getter;

@Getter
public enum Provider {
    KAKAO("kakao");

    private final String name;

    Provider(String name){
        this.name = name;
    }
}
