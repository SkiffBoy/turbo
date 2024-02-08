package com.didiglobal.turbo.engine.runner;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@ComponentScan("com.didiglobal.turbo.engine")
@MapperScan(basePackages = {"com.didiglobal.turbo.engine.dao.mapper"})
public class TestEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestEngineApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(DatabaseIdProvider.class)
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("PostgreSQL", "PostgreSQL");
        p.setProperty("Oracle", "Oracle");
        p.setProperty("MySQL", "MySQL");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}
