package com.example.ibmmqlistener.config;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

public class PrimaryDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Configure your DataSource as an AtomikosDataSourceBean for XA transactions
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

}
