package com.abapskill.configuration;

import javax.sql.DataSource;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
//import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.abapskill.entities.Vendor;
import com.abapskill.services.IVendorPersistence;

/**
 * This class uses the database connection information provided in the environment variable VCAP_SERVICES to connect to
 * the database, initializes JPA, and creates a CRUD repository instance. This is done in three steps, as explained
 * below.
 * <p>
 * As this class is registered in the Spring application context ({@link com.sap.bulletinboard.ads.ContextListener)),
 * the three methods annotated with {@literal @}Bean also are registered and used to provide bean instances of
 * {@link DataSource}, {@link EntityManagerFactory}, and {@link JpaTransactionManager}, respectively.
 * <p>
 * (Step 3) The @EnableJpaRepositories annotation (of Spring Data JPA) is used to provide a convenient repository, based
 * on JPA (EntityManager, TransactionManager).
 */
@Configuration  
//@EnableJpaRepositories(basePackageClasses = IVendorPersistence.class)
//@Profile("cloud")
public class DatabaseConfig extends AbstractCloudConfig {

    /**
     * (Step 1) Parses the local environment variable VCAP_SERVICES (containing cloud information) and provides a
     * DataSource. The superclass {@link AbstractCloudConfig}, part of the Spring Cloud plugin, is used for this.
     */
	
	@Value("${vcap.services.hdi_schema.credentials.user}")
	private String username;

	@Value("${vcap.services.hdi_schema.credentials.password}")
	private String password;

	@Value("${vcap.services.hdi_schema.credentials.url}")
	private String hostname;

	@Value("${vcap.services.hdi_schema.credentials.port}")
	private String port;

	@Value("${vcap.services.hdi_schema.credentials.schema}")
	private String schemaname;
	
	
    @Bean
    public DataSource dataSource() {
        /*
         * Load BasicDbcpPooledDataSourceCreator before TomcatJdbcPooledDataSourceCreator. Also see the following link
         * for a detailled discussion of this issue:
         * https://stackoverflow.com/questions/36885891/jpa-eclipselink-understanding-classloader-issues
         */
    	
//    	Logger cloudFoundryDataSourceConfigLogger = LoggerFactory.
    	
        List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
                "TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
                "TomcatDbcpPooledDataSourceCreator");
        DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
        
        DataSource myConnection = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("com.sap.db.jdbc.Driver")
                .url(hostname)
                .username(username)
                .password(password)
                .build();
        
       try {
    	   myConnection.getConnection().setSchema(schemaname);
       }catch (SQLException e) {
		// TODO: handle exception
       }
        

        
//        cloudFoundryDataSourceConfigLogger
        
        
        return myConnection;
    }

    /**
     * (Step 2a) Based on a {@link DataSource} (provided using the method above), provides a factory to create
     * {@link javax.persistence.EntityManager} instances (a core class of JPA). Also see
     * {@link EntityManagerFactoryProvider}.
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return EntityManagerFactoryProvider.get(dataSource, Vendor.class.getPackage().getName());
    }

    /**
     * (Step 2b) Based on an {@link EntityManagerFactory} (provided using the method above), provides a
     * {@link JpaTransactionManager} (another core class of JPA).
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}