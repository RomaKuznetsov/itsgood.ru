package com.itsgood.ru;

import com.itsgood.ru.configuration.ApplicationConfig;

import com.itsgood.ru.configuration.HibernateConfiguration;
import com.itsgood.ru.configuration.WebMVC;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import com.itsgood.ru.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.itsgood.ru")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Import({JWTConfiguration.class,
        WebSecurityConfiguration.class,
        ApplicationConfig.class,
        HibernateConfiguration.class})
@EnableCaching
@EnableTransactionManagement
public class ItsgoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItsgoodApplication.class, args);
    }

}
