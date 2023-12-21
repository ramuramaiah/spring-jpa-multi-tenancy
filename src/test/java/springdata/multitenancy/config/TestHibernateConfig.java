package springdata.multitenancy.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springdata.multitenancy.MultiTenancyApplication;
import springdata.multitenancy.TenantIdentifierResolver;
import springdata.multitenancy.TestTenantConnectionProvider;

import java.util.Properties;

@Configuration
@Profile("test")
@EnableTransactionManagement(proxyTargetClass = true)
public class TestHibernateConfig {

    @Bean
    @Profile("test")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(TestDataSourceConfig.dataSource());
        sessionFactory.setPackagesToScan(MultiTenancyApplication.class.getPackage().getName());
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                Environment.SHOW_SQL, "true");
        hibernateProperties.setProperty(
                Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT, "SCHEMA");
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT_CONNECTION_PROVIDER, TestTenantConnectionProvider.class.getName());
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, TenantIdentifierResolver.class.getName());

        return hibernateProperties;
    }
}
