package phoenix.partyquest.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyUsernamePasswordToken extends UsernamePasswordAuthenticationToken {
    private MyUserDetails myUserDetails;
    private String nickName;
    public MyUsernamePasswordToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MyUsernamePasswordToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    public MyUsernamePasswordToken(Object principal, Object credentials, String nickName,MyUserDetails myUserDetails,Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.nickName = nickName;
        this.myUserDetails = myUserDetails;
    }

    public MyUserDetails getMyUserDetails() {
        return this.myUserDetails;
    }
    public String getNickName() {
        return this.nickName;
    }
    @Override
    public Object getCredentials() {
        return super.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
