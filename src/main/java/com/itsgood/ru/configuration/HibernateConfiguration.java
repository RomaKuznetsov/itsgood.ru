package com.itsgood.ru.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration { //<property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan("com.itsgood.ru");
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    //Entity Manager
    @Primary
    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.itsgood.ru");
        em.setDataSource(dataSource);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());
        return em;
    }


    private Properties getAdditionalProperties() {
        Properties properties = new Properties();
        // See: application.properties
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.default_schema", "itsgood_ru");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}

//--//--

//@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "com.spring.app" })
//public class HibernateConfig {
//
//   @Bean
//   public LocalSessionFactoryBean sessionFactory() {
//      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//      sessionFactory.setDataSource(restDataSource());
//      sessionFactory.setPackagesToScan(new String[] { "com.spring.app.model" });
//      sessionFactory.setHibernateProperties(hibernateProperties());
//
//      return sessionFactory;
//   }
//
//   @Bean
//   public DataSource restDataSource() {
//      BasicDataSource dataSource = new BasicDataSource();
//
//      dataSource.setDriverClassName("org.postgresql.Driver");
//      dataSource.setUrl("jdbc:postgresql://localhost:5432/teste?charSet=LATIN1");
//      dataSource.setUsername("klebermo");
//      dataSource.setPassword("123");
//
//      return dataSource;
//   }
//
//   @Bean
//   @Autowired
//   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//      HibernateTransactionManager txManager = new HibernateTransactionManager();
//      txManager.setSessionFactory(sessionFactory);
//      return txManager;
//   }
//
//   @Bean
//   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//      return new PersistenceExceptionTranslationPostProcessor();
//   }
//
//   Properties hibernateProperties() {
//      return new Properties() {
//         /**
//         *
//         */
//        private static final long serialVersionUID = 1L;
//
//        {
//            setProperty("hibernate.hbm2ddl.auto", "create");
//            setProperty("hibernate.show_sql", "false");
//            setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//         }
//      };
//   }
//}
//@Configuration
//public class HibernateConfig {
//
//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
//         factory.setEntityManagerFactory(emf);
//         return factory;
//    }
//}
//public class SomeHibernateRepository {
//
//  @PersistenceUnit
//  private EntityManagerFactory emf;
//
//  protected SessionFactory getSessionFactory() {
//    return emf.unwrap(SessionFactory.class);
//  }
//
//}
