package springdata.multitenancy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springdata.multitenancy.TenantInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public TenantInterceptor requestHandler() {
        return new TenantInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHandler());
    }
}
