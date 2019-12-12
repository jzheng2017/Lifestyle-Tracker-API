package webservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class KeyConfig {
    @Value("${secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }
}
