package com.rawbank.admin.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.rawbank.admin.config")
@PropertySource(value = "file:${user.home}/rawb-appconfig/config.csc_admin/params.properties", ignoreResourceNotFound = false)
@EnableJpaRepositories(entityManagerFactoryRef = "cscEntityManagerFactory", transactionManagerRef = "cscTransactionManager", basePackages = {
		"com.rawbank.admin.sqlserver.repository" })
public class RawDBSqlServerConfig {
  // https://github.com/spring-projects/spring-boot/issues/829
	@Bean(name = "cscDataSource")
	@ConfigurationProperties(prefix = "csc.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "cscEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cscEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cscDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.rawbank.admin.sqlserver.entity").build();
	}

	@Bean(name = "cscTransactionManager")
	public PlatformTransactionManager cscTransactionManager(
			@Qualifier("cscEntityManagerFactory") EntityManagerFactory cscEntityManagerFactory) {
		return new JpaTransactionManager(cscEntityManagerFactory);
	}

}
