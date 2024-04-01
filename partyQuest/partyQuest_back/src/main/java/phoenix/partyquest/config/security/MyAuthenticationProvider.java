package phoenix.partyquest.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailService userDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        MyUserDetails findMember = (MyUserDetails) userDetailService.loadUserByUsername(email);
        if (passwordEncoder.matches(password, findMember.getPassword())) {
            return new MyUsernamePasswordToken(email, password, findMember.getMember().getName(),findMember,findMember.getAuthorities());
        }else{
            throw new BadCredentialsException("비밀번호가 일치하지 않네요");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}