package beyond.samdasoo.common.config;

import beyond.samdasoo.common.exception.JwtAuthenticationEntrypoint;
import beyond.samdasoo.common.filter.AuthenticationFilter;
import beyond.samdasoo.common.jwt.JwtAuthenticationFilter;
import beyond.samdasoo.common.jwt.JwtTokenProvider;
import beyond.samdasoo.common.jwt.service.RefreshTokenService;
import beyond.samdasoo.common.utils.CookieUtil;
import beyond.samdasoo.user.CustomUserDetails;
import beyond.samdasoo.user.service.CustomUserDetailService;
import jdk.jfr.Enabled;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;
    private final CustomUserDetailService customUserDetailService;
    private final CookieUtil cookieUtil;
    private final RefreshTokenService refreshTokenService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder()); // 비번 암호화 자동 처리
        return authenticationProvider;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, DispatcherServlet dispatcherServlet) throws Exception {

        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);

        // 인증 인가 관련
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((auth) -> auth
 //               .requestMatchers("/api/**","/swagger-ui/**", "/v3/api-docs/**").permitAll() // 테스트용
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/test/**","/api/users/login","/api/users/join",
                        "/api/users/reissue","/api/users/email/**").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
          );

        // 세션
        httpSecurity
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer
                        -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntrypoint));

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(httpSecurity.getSharedObject(AuthenticationConfiguration.class)), jwtTokenProvider,cookieUtil,refreshTokenService);
        authenticationFilter.setFilterProcessesUrl("/api/users/login"); // 로그인 경로 재설정
        httpSecurity.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider,customUserDetailService), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
