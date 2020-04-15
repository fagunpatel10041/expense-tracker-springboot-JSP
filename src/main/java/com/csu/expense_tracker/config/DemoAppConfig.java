package com.csu.expense_tracker.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;



@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.csu.expense_tracker")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
//	@Autowired
//	private JavaMailSender javaMailSender;
	
	
	
	// set up a logger for diagnostics
		private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for ViewResolver

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	
	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		try {
			securityDataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		
		// set connection pool props
				securityDataSource.setInitialPoolSize(
				getIntProperty("connection.pool.initialPoolSize"));

				securityDataSource.setMinPoolSize(
						getIntProperty("connection.pool.minPoolSize"));
				
				securityDataSource.setMaxPoolSize(
						getIntProperty("connection.pool.maxPoolSize"));
				
				securityDataSource.setMaxIdleTime(
						getIntProperty("connection.pool.maxIdleTime"));
		
		
		
		return securityDataSource;
		
	}
	
	private Properties getHibernateProperties() {
		
		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
		
	}
	
	// need a helper method 
		// read environment property and convert to int
		
		private int getIntProperty(String propName) {
			
			String propVal = env.getProperty(propName);
			
			// now convert to int
			int intPropVal = Integer.parseInt(propVal);
			
			return intPropVal;
		}
		
		@Bean
		public LocalSessionFactoryBean sessionFactory(){
			
			// create session factorys
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			
			// set the properties
			sessionFactory.setDataSource(securityDataSource());
			sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
			sessionFactory.setHibernateProperties(getHibernateProperties());
			
			return sessionFactory;
		}
		
		@Bean
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
			
			// setup transaction manager based on session factory
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory);

			return txManager;
		}
		
		@Bean
		public JavaMailSender javaMailSender() {
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(env.getProperty("spring.mail.host"));
			mailSender.setPort(587);
			mailSender.setUsername(env.getProperty("spring.mail.username"));
			mailSender.setPassword(env.getProperty("spring.mail.password"));
			
			mailSender.setJavaMailProperties(getJavaMailProperties());
			
			
			
			return mailSender;
			
		}
		
		
		private Properties getJavaMailProperties() {
			
			Properties props = new Properties();
			
			props.setProperty("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
			props.setProperty("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
			props.setProperty("mail.smtp.connectiontimeout", env.getProperty("spring.mail.properties.mail.smtp.connectiontimeout"));
			props.setProperty("mail.smtp.timeout", env.getProperty("spring.mail.properties.mail.smtp.timeout"));
			props.setProperty("mail.smtp.writetimeout", env.getProperty("spring.mail.properties.mail.smtp.writetimeout"));
			

			return props;
			
		}
		
		
		
	
	
}









