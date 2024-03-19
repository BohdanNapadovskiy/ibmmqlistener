package com.example.ibmmqlistener.config;


import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import javax.transaction.SystemException;
import java.util.Properties;

@Configuration
public class TransactionManagerConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean orderDataSource() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName("xaDataSource");
        dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties properties = new Properties();
        properties.setProperty("serverName", "localhost");
        properties.setProperty("portNumber", "5432");
        properties.setProperty("databaseName", "habrdb");
        properties.setProperty("user", "habrpguser");
        properties.setProperty("password", "pgpwd4habr");
        dataSource.setXaProperties(properties);
        dataSource.setPoolSize(5);
        return dataSource;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }
    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setForceShutdown(false);
        return manager;
    }




}
