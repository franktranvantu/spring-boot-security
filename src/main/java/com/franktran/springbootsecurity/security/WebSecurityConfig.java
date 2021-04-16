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
                .antMatchers("/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails frank = User.builder()
                .username("frank")
                .password(passwordEncoder.encode("frank123"))
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails henry = User.builder()
                .username("henry")
                .password(passwordEncoder.encode("henry123"))
                .authorities(ADMINTRAINEE.getAuthorities())
                .build();

        UserDetails bean = User.builder()
                .username("bean")
                .password(passwordEncoder.encode("bean123"))
                .authorities(STUDENT.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(frank, henry, bean);
    }
}
