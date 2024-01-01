package com.example.application.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
//    @Autowired
//    private DataSource dataSource;
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan("com.example.application.model.entity");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        // Configure Hibernate properties here, e.g., dialect, show_sql, etc.
//        return properties;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:5555/estatebasic");
//        dataSource.setUsername("root");
//        dataSource.setPassword("N2g1h0i7@");
//        return dataSource;
//    }
}
