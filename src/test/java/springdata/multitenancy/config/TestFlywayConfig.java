package springdata.multitenancy.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springdata.multitenancy.TenantContext;

@Configuration
@Profile("test")
public class TestFlywayConfig {

    @Bean
    @Profile("test")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .locations("db/test/migration/default_tenant")
                .dataSource(TestDataSourceConfig.dataSource())
                .schemas(TenantContext.DEFAULT_TENANT_ID)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    @Profile("test")
    public String tenantSchemaLocation() {
        return "db/test/migration/tenants";
    }
}
