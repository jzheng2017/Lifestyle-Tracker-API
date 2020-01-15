package webservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.configs.AppConfig;

@Service
public class AppConfigService {
    private AppConfig appConfig;

    @Autowired
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    /**
     * Get the expiration time for a JWT token
     *
     * @return expiration time in seconds
     */
    public int getTokenExpiration() {
        final int expirationTimeInSeconds = Integer.parseInt(appConfig.getExpiration());
        final int defaultExpirationTimeInSeconds = 3600; // 1 hour

        if (expirationTimeInSeconds > 0) {
            return expirationTimeInSeconds;
        } else {
            return defaultExpirationTimeInSeconds; //default to 1 hour if expiration time config is empty or 0
        }
    }
}
