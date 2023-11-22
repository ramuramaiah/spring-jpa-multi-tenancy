package springdata.multitenancy.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springdata.multitenancy.TenantContext;
import springdata.multitenancy.repository.UserRepository;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/default_tenant")
                .dataSource(dataSource)
                .schemas(TenantContext.DEFAULT_TENANT_ID)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, DataSource dataSource) {
        return args -> {
            userRepository.findAll().forEach(user -> {
                String tenant = user.getUsername();
                Flyway flyway = Flyway.configure()
                        .locations("db/migration/tenants")
                        .dataSource(dataSource)
                        .schemas(tenant)
                        .load();
                flyway.migrate();
            });
        };
    }
}
