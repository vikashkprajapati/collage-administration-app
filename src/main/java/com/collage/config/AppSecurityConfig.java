package com.collage.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final com.collage.security.JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/plugins/**", "/css/**", "/js/**", "/images/**", "/styles/**");
        web.ignoring().antMatchers("/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**", "/img/**",
                "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/web/**")
                .permitAll()
                .antMatchers("/user-guide/").permitAll()
                .antMatchers("/user-guide").permitAll()
                .antMatchers("/download/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/public/api/v1/**")
                .permitAll()
                .antMatchers("/auth/**")
                .permitAll().antMatchers("/css/**")
                .permitAll().antMatchers("/js/**")
                .permitAll().antMatchers("/plugins/**")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public com.collage.security.JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new com.collage.security.JwtAuthenticationFilter();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new com.collage.exception.CustomAccessDeniedHandler();
    }
}