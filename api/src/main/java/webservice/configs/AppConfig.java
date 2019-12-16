package webservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {
    @Value("${secret}")
    private String secret;
    @Value("${expiration}")
    private String expiration;

    public String getSecret() {
        return secret;
    }

    public String getExpiration(){return expiration;}
}
