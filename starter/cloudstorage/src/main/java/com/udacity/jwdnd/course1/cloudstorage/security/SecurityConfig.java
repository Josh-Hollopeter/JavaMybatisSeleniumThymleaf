package com.udacity.jwdnd.course1.cloudstorage.security;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http 
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.headers().frameOptions().sameOrigin();
        http
        .authorizeRequests()
        .antMatchers("**").permitAll()
        .antMatchers("/register**").permitAll()
        .antMatchers( "/public/**").permitAll()  
        .anyRequest().authenticated()  
            .and()  
        .formLogin()  
            .loginPage("/login.html")  
            .failureUrl("/login-error.html")  
            .permitAll(); 

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT username, password,enabled FROM Users WHERE username=?";
        String authQuery = "Select username, role from Users WHERE username = ?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
    }
}
