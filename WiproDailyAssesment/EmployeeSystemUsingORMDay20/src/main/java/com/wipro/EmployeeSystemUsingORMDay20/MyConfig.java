package com.wipro.EmployeeSystemUsingORMDay20;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.wipro.EmployeeSystemUsingORMDay20")
public class MyConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/demo");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.wipro.EmployeeSystemUsingORMDay20");
        sf.setHibernateProperties(hibernateProperties());
        return sf;
    }

    private Properties hibernateProperties() {
        Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        p.put("hibernate.show_sql", "true");
        p.put("hibernate.hbm2ddl.auto", "update");
        return p;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory().getObject());
        return tm;
    }

//    @Bean
//    public EmployeeDao employeeDao() {
//        return new EmployeeDaoImpl();
//    }
}