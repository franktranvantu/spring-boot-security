package com.franktran.springbootsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.concurrent.TimeUnit;

import static com.franktran.springbootsecurity.security.UserRole.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/show-login").permitAll()
                    .loginProcessingUrl("/process-login")
                    .defaultSuccessUrl("/management/students")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(20)).key("frank")
                    .rememberMeParameter("remember")
                .and().logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/show-login");

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails frank = User.builder()
                .username("frank@gmail.com")
                .password(passwordEncoder.encode("frank123"))
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails henry = User.builder()
                .username("henry@gmail.com")
                .password(passwordEncoder.encode("henry123"))
                .authorities(ADMINTRAINEE.getAuthorities())
                .build();

        UserDetails bean = User.builder()
                .username("bean@gmail.com")
                .password(passwordEncoder.encode("bean123"))
                .authorities(STUDENT.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(frank, henry, bean);
    }
}
