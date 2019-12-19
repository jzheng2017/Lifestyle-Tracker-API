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
        final int expirationTime = Integer.parseInt(appConfig.getExpiration());
        final int defaultExpirationTime = 3600; // 1 hour

        if (expirationTime > 0) {
            return expirationTime;
        } else {
            return defaultExpirationTime; //default to 1 hour if expiration time config is empty or 0
        }
    }
}
