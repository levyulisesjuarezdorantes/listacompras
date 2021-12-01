package com.mexico.listacompras;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
public class HibernateConfig {

	private static Logger LOGGER = LoggerFactory.getLogger(HibernateConfig.class);

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private String showSql;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	/*
	 * @Value("${spring.jpa.hibernate.naming.physical-strategy}") private String
	 * strategy;
	 */
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", dialect);
		//prop.setProperty("hibernate.id.new_generator_mappings", "false");
		//prop.setProperty("org.hibernate.envers.audit_table_prefix", "audited_");
		//prop.setProperty("org.hibernate.envers.audit_table_suffix", "");
		//prop.setProperty("org.hibernate.envers.revision_field_name", "rev");
		//prop.setProperty("org.hibernate.envers.revision_type_field_name", "revtype");
		prop.setProperty("hibernate.show_sql", this.showSql.toString());
		//prop.setProperty("hibernate.ddl-auto", this.ddlAuto);
		
//		prop.setProperty("hibernate.naming.physical-strategy", this.strategy);	
		
		prop.setProperty("spring.jpa.hibernate.ddl-auto", this.ddlAuto);
		prop.setProperty("hibernate.hbm2ddl.auto", this.ddlAuto);
		
		
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setHibernateProperties(prop);
		sessionFactory.setPackagesToScan("com.mexico.listacompras.entities");

		try {
			LOGGER.info("Connecting to {}", url);
			Connection connection = dataSource.getConnection();
			LOGGER.info("Connected to {} OK !!", connection.getMetaData().getURL());
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException("DB connection test failed", e);
		}

		return sessionFactory;
	}
}
