package phoenix.partyquest.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import phoenix.partyquest.config.security.JwtProvider;
import phoenix.partyquest.constants.SecurityConstants;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String accessToken = jwtProvider.resolveJWTFromRequest(request);
        if (null != accessToken) {
            if (!jwtProvider.validateToken(accessToken)) {
                Cookie[] cookies = request.getCookies();
                String refreshToken;
                for (Cookie cookie : cookies) {
                    if (!cookie.getName().equals("refreshtoken")) continue;
                    refreshToken = cookie.getValue();
                    String userEmail = null; // TODO: refresh token에서 user email 얻고 없으면 바로 invalidate => repository
                    try {
                        Claims refreshClaims = jwtProvider.getJwtClaimsFromToken(refreshToken);
                        userEmail = refreshClaims.getSubject();
                    } catch (Exception e) {
                        jwtProvider.invalidateRefreshToken(refreshToken);
                        response.addCookie(jwtProvider.createEmptyCookie());
                        filterChain.doFilter(request, response);
                        // TODO: 403에러가 내려가면 클라이언트에서 authStore 초기화 해주어야한다.
                        return;
                    }
                    if (jwtProvider.isRefreshTokenValid(userEmail, refreshToken)) {
                        String newAccessToken = jwtProvider.createAccessJWT(userEmail);
                        reauthenticateMemberWithNewAccessToken(newAccessToken);
                        response.addHeader(SecurityConstants.JWT_HEADER, newAccessToken);
                    }else{
                        jwtProvider.isAccessTokenValid(userEmail);
                    }
                }
            }else{
                Claims accessClaims = jwtProvider.getJwtClaimsFromToken(accessToken);
                if (jwtProvider.isAccessTokenValid(accessClaims.getSubject())) {
                    authenticateMember(accessToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/member/login");
    }

    private void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(jwtProvider.createAuthentication(username));
        SecurityContextHolder.setContext(context);
    }
    private void reauthenticateMemberWithNewAccessToken(String accessToken) {
        Claims accessTokenClaims = jwtProvider.getJwtClaimsFromToken(accessToken);
        String userEmail = accessTokenClaims.getSubject();
        setAuthentication(userEmail);
    }

    private void authenticateMember(String accessToken) {
        Claims claims = jwtProvider.getJwtClaimsFromToken(accessToken);
        String userEmail = claims.getSubject();
        String authorities = (String) claims.get("authorities");
        Authentication auth = new UsernamePasswordAuthenticationToken(userEmail, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }
}