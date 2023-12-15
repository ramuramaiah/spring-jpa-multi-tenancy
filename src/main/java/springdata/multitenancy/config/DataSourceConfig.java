package springdata.multitenancy.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DataSourceConfig {
    public static DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        Resource resource = new ClassPathResource("/application.properties");
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            props = new Properties();
        }
        dataSourceBuilder.url(props.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(props.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(props.getProperty("spring.datasource.password"));
        return dataSourceBuilder.build();
    }
}
