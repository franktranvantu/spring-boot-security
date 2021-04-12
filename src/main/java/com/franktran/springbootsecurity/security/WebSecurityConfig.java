package com.franktran.springbootsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/students/public").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails frank = User.builder()
                .username("frank")
                .password(passwordEncoder.encode("frank123"))
                .roles("ADMIN")
                .build();

        UserDetails henry = User.builder()
                .username("henry")
                .password(passwordEncoder.encode("henry123"))
                .roles("ADMINTRAINEE")
                .build();

        UserDetails bean = User.builder()
                .username("bean")
                .password(passwordEncoder.encode("bean123"))
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(frank, henry, bean);
    }
}
