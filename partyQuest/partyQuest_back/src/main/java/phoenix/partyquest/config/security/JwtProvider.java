package phoenix.partyquest.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import phoenix.partyquest.constants.SecurityConstants;
import phoenix.partyquest.domain.token.AccessToken;
import phoenix.partyquest.domain.token.RefreshToken;
import phoenix.partyquest.repository.AccessTokenRepository;
import phoenix.partyquest.repository.RefreshTokenRepository;

import javax.crypto.SecretKey;
import java.net.URLEncoder;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value(value = "${jwt.secret.key}")
    private String jwtSecretKey;
    private SecretKey key;
    private final MyUserDetailService userDetailService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;
    private Map<String, String> jwtHeader = new HashMap<>();

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(UTF_8));
    }

    public String resolveJWTFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(SecurityConstants.JWT_HEADER);
        return extractTokenFromBearer(bearer);
    }

    public String extractTokenFromBearer(String bearer) {
        if (StringUtils.hasText(bearer) && bearer.startsWith(SecurityConstants.JWT_BEARER_PREFIX)) {
            return bearer.substring(7);
        }
        return null;
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            log.error("Token exception", e);
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Transactional
    public boolean isAccessTokenValid(String userEmail) {
        AccessToken findToken = accessTokenRepository.findAccessTokenByUserEmail(userEmail).orElseThrow();
        if (findToken.isValid()) {
            return true;
        } else {
            findToken.invalidate();
        }
        return false;
    }
    @Transactional
    public boolean isRefreshTokenValid(String userEmail,String refreshToken) {
        RefreshToken findToken = refreshTokenRepository.findRefreshTokenByUserEmail(userEmail).orElseThrow();
        String refreshTokenVal = findToken.getRefreshToken();
        if (validateToken(refreshToken) && findToken.isValid()) {
            if(refreshTokenVal.equals(refreshToken)) return true;
        }else{
            findToken.invalidate();
        }
        return false;
    }

    @Transactional
    public void invalidateRefreshToken(String refreshToken) {

        RefreshToken findRefreshToken = refreshTokenRepository.findRefreshTokenByRefreshToken(refreshToken).orElseThrow();
        findRefreshToken.invalidate();
        log.info("invalidate refresh token");
    }
    /**
     * 새로 등록한 유저에게 jwt를 발행한다.(회원 가입후에 사용)
     */
    public String createAccessJWT(Authentication authentication) {
        MyUsernamePasswordToken custAuth = (MyUsernamePasswordToken) authentication;

        return Jwts.builder()
                .setIssuer(SecurityConstants.JWT_ISSUER)
                .setSubject(authentication.getName())
                .claim("hostId", custAuth.getMyUserDetails().getMember().getId())
                .claim("nickname", custAuth.getNickName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.JWT_ACCESS_LIFE_SPAN))
                .signWith(key)
                .compact();
    }

    public String createAccessJWT(String email) {
        MyUserDetails userDetails = (MyUserDetails) userDetailService.loadUserByUsername(email);
        return Jwts.builder()
                .setIssuer(SecurityConstants.JWT_ISSUER)
                .setSubject(userDetails.getUsername())
                .claim("hostId",userDetails.getMember().getId())
                .claim("nickname",userDetails.getMember().getName())
                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.JWT_ACCESS_LIFE_SPAN))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshJWT(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuer(SecurityConstants.JWT_ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.JWT_REFRESH_LIFE_SPAN))
                .signWith(key)
                .compact();
    }
    public Cookie createCookie(String email) {
        String cookieName = "refreshtoken";
        String cookieValue = createRefreshJWT(email);
        var RTcookie = URLEncoder.encode(cookieValue, UTF_8);
        Cookie cookie = new Cookie(cookieName, RTcookie);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(SecurityConstants.JWT_COOKIE_MAX_AGE);
        return cookie;
    }
    public Cookie createEmptyCookie() {
        log.info("create empty cookie");
        String cookieName = "refreshtoken";
        String cookieValue = "empty-dragon";
        var RTcookie = URLEncoder.encode(cookieValue, UTF_8);
        Cookie cookie = new Cookie(cookieName, RTcookie);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(SecurityConstants.JWT_COOKIE_MAX_AGE);
        return cookie;
    }
    public Claims getJwtClaimsFromToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
