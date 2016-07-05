package org.filho.litecommerce.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix="database.datasource")
public class DatasourceConfig extends HikariConfig {
  
  @Bean
  public DataSource dataSource() {
    return new HikariDataSource(this);
  }
  
}
