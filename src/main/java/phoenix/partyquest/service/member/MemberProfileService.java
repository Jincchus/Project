package phoenix.partyquest.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.response.member.profile.MemberProfileViewResp;
import phoenix.partyquest.repository.member.MemberRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberProfileService {
    private final MemberRepository memberRepository;

    public MemberProfileViewResp getMemberProfile() {
        return null;
    }

}
