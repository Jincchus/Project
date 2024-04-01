package kr.co.lotteon.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .formLogin(login->login.loginPage("/member/login")
                        .usernameParameter("uid")
                        .passwordParameter("pass")
                                .defaultSuccessUrl("/",true)
//                        .successHandler((request, response, authentication) -> {
//                            //TODO: 기존에 요청했던 url로 로그인 처리후 redirect
//                            response.sendRedirect("/");
//                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/member/logout")
                        .logoutSuccessUrl("/")
                )
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/","/index").permitAll()
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("SELLER","ADMIN")
                        .requestMatchers("/qna/write").authenticated()
                        .anyRequest().permitAll() // 추가
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //static resources security필터 거치지 않도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()
                    .requestMatchers(
                            "/css/**",
                            "/js/**",
                            "/images/**"
                    );
        };
    }
}
