package com.udacity.jdnd.course3;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("com.udacity.datasource")
    public DataSource getDatasource(){
        DataSourceBuilder dsb = DataSourceBuilder.create();
        dsb.url("jdbc:mysql://localhost:3306/plant");
        return dsb.build();
    }

/*    private String securePasswordService() {
        return "sa1234";
    }

 */

}

