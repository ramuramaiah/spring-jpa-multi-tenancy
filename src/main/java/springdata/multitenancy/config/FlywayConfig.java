package springdata.multitenancy.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springdata.multitenancy.TenantContext;
import springdata.multitenancy.dao.UserDAO;

@Configuration
@Profile("!test")
public class FlywayConfig {

    @Autowired
    UserDAO userDao;
    @Bean
    @Profile("!test")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/default_tenant")
                .dataSource(DataSourceConfig.dataSource())
                .schemas(TenantContext.DEFAULT_TENANT_ID)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    @Profile("!test")
    public String tenantSchemaLocation() {
        return "db/migration/tenants";
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userDao.findAll().forEach(user -> {
                String tenant = user.getUsername();
                Flyway flyway = Flyway.configure()
                        .locations("db/migration/tenants")
                        .dataSource(DataSourceConfig.dataSource())
                        .schemas(tenant)
                        .load();
                flyway.migrate();
            });
        };
    }
}
