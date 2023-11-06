package kr.co.lotteon.config.security;

import kr.co.lotteon.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    public MyUserDetails(Member member) {
        this.member = member;
    }

    private Member member; // 여러 필드를 정의할 필요없이 엔티티를 바로 이용한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPass();
    }

    @Override
    public String getUsername() {
        return member.getUid();
    }

    //TODO: user valid 로직 작성하기
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return (!member.getLevel().name().equals("INVALID"));
    }
}
