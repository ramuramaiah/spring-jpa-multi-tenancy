package springdata.multitenancy.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class TenantService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private String tenantSchemaLocation;

    public void initDatabase(String schema) {
        Flyway flyway = Flyway.configure()
                .locations(tenantSchemaLocation)
                .dataSource(dataSource)
                .schemas(schema)
                .load();
        flyway.migrate();
    }
}
