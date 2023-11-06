package com.employeeManagement.emp.databases.postgreSQL.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresqlDBEntityManagerFactoryBean",
        basePackages = {"com.employeeManagement.emp.databases.postgreSQL.repository;"},
        transactionManagerRef = "postgresqlDBTransactionManager"
)
public class postgreSQLConfig {


    @Autowired
    private Environment environment;


//    Datasourse
    @Bean(name = "postgresqlDB")
    @Primary
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("mongodb.datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("mongodb.datasource.Driver-class-name"));
        dataSource.setUsername(environment.getProperty("mongodb.datasource.username"));
        dataSource.setPassword(environment.getProperty("mongodb.datasource.password"));
        return dataSource;
    }



//    entity manager
    @Bean(name = "postgresqlDBEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean been = new LocalContainerEntityManagerFactoryBean();
        been.setDataSource((dataSource()));
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        been.setJpaVendorAdapter(adapter);
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show-sql","true");
        props.put("hibernate.ddl","update");
        been.setJpaPropertyMap(props);

        been.setPackagesToScan("package com.employeeManagement.emp.databases.postgreSQL.entity;");
        return been;
    }



//    platform transection management
    @Bean(name = "postgresqlDBTransactionManager" )
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }
}
