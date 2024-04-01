package phoenix.partyquest.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import phoenix.partyquest.filter.JWTTokenValidatorFilter;

import java.util.Arrays;
import java.util.Collections;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    //static resources security필터 거치지 않도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()
                    .requestMatchers(
                            "/css/**",
                            "/js/**",
                            "/images/**",
                            "/error"
                    );
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http//.securityContext(ctxt-> ctxt.requireExplicitSave(false)) // jwt: ALWAYS -> STATELESS
                .httpBasic(req->req.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // jwt: ALWAYS -> STATELESS
                .cors(cust -> cust.configurationSource(
                        req -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:5173")
                            );
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(Arrays.asList("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        }
                ))
//                .csrf(csrf->csrf.csrfTokenRequestHandler(requestHandler)
//                        .ignoringRequestMatchers("/api/study/create","/api/member/sign-up","/api/member/login","/api/study/changeApplicationStatus","/api/study/updateLike")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
//                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // 로그인 이후에 체크
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTokenValidatorFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)

//                .formLogin(login -> login.loginProcessingUrl("/login")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .successHandler(authenticationSuccessHandler())
//                )
                .formLogin(login->login.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/api/member/sign-out").authenticated()
                        .anyRequest().permitAll() // 추가
                );
            return http.build();
        }

        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
            return new MyLoginSuccessHandler();
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}
