package springdata.multitenancy.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springdata.multitenancy.MultiTenancyApplication;
import springdata.multitenancy.TenantConnectionProvider;
import springdata.multitenancy.TenantIdentifierResolver;

import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(DataSourceConfig.dataSource());
        sessionFactory.setPackagesToScan(MultiTenancyApplication.class.getPackage().getName());
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                Environment.SHOW_SQL, "true");
        hibernateProperties.setProperty(
                Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT, "DATABASE");
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT_CONNECTION_PROVIDER, TenantConnectionProvider.class.getName());
        hibernateProperties.setProperty(
                Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, TenantIdentifierResolver.class.getName());

        return hibernateProperties;
    }
}
