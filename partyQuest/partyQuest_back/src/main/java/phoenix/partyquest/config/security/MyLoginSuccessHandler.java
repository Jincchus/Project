package phoenix.partyquest.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;


public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        setDefaultTargetUrl("/");
        // 기존에 요청했던 url이 있다면 그리로 리다이렉트 해준다.
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
