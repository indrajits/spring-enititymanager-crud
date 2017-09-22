package com.springmvcenititymanagercrud.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springmvcenititymanagercrud")
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		/*viewResolver.setViewClass(JstlView.class);*/
		viewResolver.setPrefix("/WEB-INF/web-service-application/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
			
	@Bean(name = "dataSource")
	public DataSource dataSource(){
	   BasicDataSource dataSource = new BasicDataSource();
	   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	   dataSource.setUrl("jdbc:mysql://mysql-aws.cibjqsarb1jj.ap-south-1.rds.amazonaws.com/cvs");
	   dataSource.setUsername("indrajit_sen");
	   dataSource.setPassword("H4l2ni43");
	   return dataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	   LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	   em.setDataSource(dataSource());
	   em.setPackagesToScan(new String[] {"com.springmvcenititymanagercrud.entities"});
	   JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	   em.setJpaVendorAdapter(vendorAdapter);
	   em.setJpaProperties(additionalProperties());
	   return em;
	}	

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(){
	   JpaTransactionManager transactionManager = new JpaTransactionManager();
	   transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	   return transactionManager;
	}

	private Properties additionalProperties() {
	   return new Properties() {
	      {  // Hibernate Specific:
	         setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	         setProperty("format_sql", "true");
	         setProperty("hibernate.show_sql", "true");
	         setProperty("hibernate.jdbc.batch_size", "20");
	         setProperty("hibernate.order_inserts", "true");
	         setProperty("hibernate.order_updates", "true");
	         setProperty("hibernate.jdbc.batch_versioned_data", "true");
	      }
	   };
	}
}
