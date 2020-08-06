package HCY.simpleboard.config.auth;

import HCY.simpleboard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
/** Spring Security 설정을 활성화 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /** H2 console 화면 사용을 위한 설정. **/
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                /** URL 별 권환 관리 설정 옵션 시작점. 이게 있어야 antMatchers 사용 가능.**/
                .authorizeRequests()
                /** 권한 관리 대상 지정 옵션 (URL, HTTP 메소드별로 관리 가능) **/
                .antMatchers("/", "/css/**", "/images/**","/js/**",
                        "h2-console/**").permitAll()
                .antMatchers("/post/**").hasRole(Role.USER.name())
                /** 설정된 URL 이외의 나머지 URL들에 대해서 인증된 사용자에게만 접근 허용 (로그인한 사용자들) **/
                .anyRequest().authenticated()
            .and()
                /** 로그아웃 기능 설정 진입지점.. **/
                .logout()
                /** 로그아웃 성공 시 / 주소로 이동 **/
                    .logoutSuccessUrl("/")
            .and()
                /** OAuth2 로그인 기능 설정 진입지점.. **/
                .oauth2Login()
                /** OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당.. **/
                    .userInfoEndpoint()
                /** 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록 **/
                        .userService(customOAuth2UserService);
    }
}
