package phoenix.partyquest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.time.Duration;

@Slf4j
@Configuration
public class WebCacheConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CacheControl cacheControl = CacheControl.maxAge(Duration.ofMinutes(30))
                .noTransform()
                .mustRevalidate();

        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
        webContentInterceptor.addCacheMapping(cacheControl, "/api/*/cached/**");

        registry.addInterceptor(webContentInterceptor);
    }
}
