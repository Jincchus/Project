package phoenix.partyquest.config.security.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.oauth2.OAuth2UserInfo;
import phoenix.partyquest.domain.member.oauth2.Provider;
import phoenix.partyquest.domain.member.oauth2.kakao.KakaoUserInfo;
import phoenix.partyquest.repository.member.MemberRepository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2의 정보 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}", oAuth2User.getAttributes());
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String loginId = provider + "_" +providerId;


        Optional<Member> optionalUser = memberRepository.findByLoginId(loginId);
        Member member;

        if(optionalUser.isEmpty()) {
            member = Member.builder()
                    .loginId(loginId)
                    .name(oAuth2User.getAttribute("name"))
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(member);
        } else {
            member = optionalUser.get();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

    private OAuth2UserInfo generateMemberInfo(String provider, Map<String, Object> attributes) {
        if(provider.equals(Provider.KAKAO.getName())) {
            log.info(" - generateMemberInfo > provider_KAKAO");
            return new KakaoUserInfo(attributes);
        }
        log.info(" - generateMemberInfo > 로그인 실패");
        return null;
    }

}
