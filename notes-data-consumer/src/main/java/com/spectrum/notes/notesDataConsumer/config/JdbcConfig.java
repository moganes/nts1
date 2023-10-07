package com.spectrum.notes.notesDataConsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * The Class JdbcConfig.
 *
 * @author alam
 */
@Configuration
public class JdbcConfig {

    /**
     * Entity manager factory.
     *
     * @param ds the ds
     * @return the local container entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource ds,
            @Value("${spring.jpa.show-sql}") boolean showSQL,
            @Value("${spring.jpa.generate-ddl}") boolean generateDDL,
            @Value("${spring.jpa.hibernate.order_inserts}") boolean orderInsert,
            @Value("${spring.jpa.hibernate.order_updates}") boolean orderUpdate,
            @Value("${spring.jpa.hibernate.generate_statistics}") boolean generateStatistics,
            @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
            @Value("${spring.jpa.hibernate.jdbc.batch_size}") int batchSize,
            @Value("${spring.jpa.hibernate.jdbc.batch_versioned_data}") boolean batchVersion
    ) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(generateDDL);
        vendorAdapter.setShowSql(showSQL);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.jdbc.batch_size", batchSize);
        jpaProperties.put("hibernate.order_inserts", orderInsert);
        jpaProperties.put("hibernate.order_updates", orderUpdate);
        jpaProperties.put("hibernate.jdbc.batch_versioned_data", batchVersion);
        jpaProperties.put("hibernate.generate_statistics", generateStatistics);
        jpaProperties.put("hibernate.ddl-auto", ddlAuto);
        //jpaProperties.put("hibernate.dialect.storage_engine", "innodb");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        // jpaProperties.put("spring.jpa.properties.hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
       // org.hibernate.dialect.MySQL8Dialect
        // org.hibernate.dialect.MySQL8Dialect

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.spectrum.notes.notesDataConsumer.model");
        factory.setDataSource(ds);
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    /**
     * Transaction manager.
     *
     * @param entityManagerFactory the entity manager factory
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return txManager;
    }
}