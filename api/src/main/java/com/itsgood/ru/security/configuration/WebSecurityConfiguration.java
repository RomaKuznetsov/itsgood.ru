package com.itsgood.ru.security.configuration;


import com.itsgood.ru.filters.AuthenticationTokenFilter;
import com.itsgood.ru.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final UserDetailsService userProvider;
    private final TokenProvider tokenUtils;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder encoder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager) {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtils, userProvider);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
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
                /*For swagger access only*/
                        antMatchers("/v3/api-docs/**", "/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html#").permitAll()
                .antMatchers("/actuator/**").permitAll().
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
        //.and().sessionManagement(session ->session.
        //                        sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1).
        //                        maxSessionsPreventsLogin(false)) нужен бин сессии

        // Custom JWT based authentication
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
        //For swagger access only

    }

    //For swagger access only
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v3/api-docs/**", "/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**");
    }

}
