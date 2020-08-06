package HCY.simpleboard.config;

import HCY.simpleboard.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/** LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가 **/
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    /** HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolver를 통해 추가해야만 한다. **/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}
