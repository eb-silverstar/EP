package com.edu.portal.config;

import com.edu.portal.tenant.TenantDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Value("${tenants.path}")
    String tenantsPath;

    @Bean
    @ConfigurationProperties(prefix = "tenant")
    public DataSource dataSource() {
        File[] files = Paths.get(tenantsPath).toFile().listFiles();
        Map<Object, Object> dataSource = new HashMap<>();

        for(File propertyFile : files) {
            Properties properties = new Properties();
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

            try {
                properties.load(new FileInputStream(propertyFile));
                String tenant = properties.getProperty("name");

                dataSourceBuilder.driverClassName(properties.getProperty("datasource.driver-class-name"));
                dataSourceBuilder.username(properties.getProperty("datasource.username"));
                dataSourceBuilder.password(properties.getProperty("datasource.password"));
                dataSourceBuilder.url(properties.getProperty("datasource.url"));

                dataSource.put(tenant, dataSourceBuilder.build());

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        AbstractRoutingDataSource tenantDataSource = new TenantDataSource();
        tenantDataSource.setTargetDataSources(dataSource);
        tenantDataSource.afterPropertiesSet();

        return tenantDataSource;
    }

}
