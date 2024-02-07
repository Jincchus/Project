package phoenix.partyquest.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.repository.member.MemberRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}",username);
        Member findMember = memberRepository.findByEmail(username).orElseThrow();
        return new MyUserDetails(findMember);
    }
}