package PetBridge.common.config;

import PetBridge.auth.jwt.annotation.ValidMemberResolver;
import PetBridge.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;
    private final ValidMemberResolver validMemberResolver;

    public WebConfig(JwtInterceptor jwtInterceptor, ValidMemberResolver validMemberResolver) {
        this.jwtInterceptor = jwtInterceptor;
        this.validMemberResolver = validMemberResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")  // 인터셉터를 적용할 URL 패턴
                .excludePathPatterns("/api/v1/auth/login", "/api/v1/auth/signup", "/api/v1/email/emailCode");  // 예외 URL
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(validMemberResolver);  // ArgumentResolver 등록
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "https://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
