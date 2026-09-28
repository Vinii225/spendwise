package backend.config;

import backend.interceptor.AuthInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public InterceptorConfiguration(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/auth",
                        "/auth/**",
                        "/css/**",
                        "/js/**",
                        "/imagens/**",
                        "/favicon.ico",
                        "/error"
                );
    }
}