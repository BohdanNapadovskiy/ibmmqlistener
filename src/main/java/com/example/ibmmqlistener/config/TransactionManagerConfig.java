package com.example.ibmmqlistener.config;


import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import java.util.Properties;

@Configuration
public class TransactionManagerConfig {
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;


    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean orderDataSource() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName("xaDataSource");
        dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties properties = new Properties();
        properties.setProperty("serverName", "localhost");
        properties.setProperty("portNumber", "5432");
        properties.setProperty("databaseName", "habrdb");
        properties.setProperty("user", userName);
        properties.setProperty("password", password);
        dataSource.setXaProperties(properties);
        dataSource.setPoolSize(5);
        return dataSource;
    }

}
