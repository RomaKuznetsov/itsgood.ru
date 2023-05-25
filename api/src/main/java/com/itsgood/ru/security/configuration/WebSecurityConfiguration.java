package com.itsgood.ru.security.configuration;


import com.itsgood.ru.security.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder encoder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                //отключение крос доменных запросов
                // запрещено выполнение запросов от других приложений
                        csrf().
                disable().
                // влключена обработка всех исключений по работе безопастности
                        exceptionHandling().
                and().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                //название метода и описание урл и разрешение кому есть доступ к этому урл
                //permitAll() - разрешено всем
                //hasAnyRole() - и перечислить всех кому разрешен доступ
                //можно разрешить всем доступ ко всем url и методам get или только администратору
                //проверка на роботоспособность сервера
                        antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
                antMatchers("/guest/**").permitAll().
                antMatchers("/registration/**").permitAll().
                antMatchers("/authentication/**").permitAll().
                antMatchers("/controller/rest/spring/**").permitAll().
                antMatchers("/rest/**").permitAll().
                antMatchers("/rest/hibernate/**").permitAll().
                antMatchers("/rest/hibernate/category").permitAll().
                antMatchers("/admin/**").hasRole("administrator").
                anyRequest().authenticated();
    }
}
