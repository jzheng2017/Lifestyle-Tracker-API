package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webservice.configs.AppConfig;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppConfigServiceTest {
    @InjectMocks
    private AppConfigService appConfigService;
    @Mock
    private AppConfig appConfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTokenExpirationTimeReturnsInt() {
        final String expirationTime = "69";
        final int expectedValue = 69;

        when(appConfig.getExpiration()).thenReturn(expirationTime);

        Assertions.assertEquals(expectedValue, appConfigService.getTokenExpiration());
    }

    @Test
    public void getTokenExpirationTimeDefaultsTo3600IfRetrievedExpirationTimeIsZeroOrLower() {
        final String expirationTimeZero = "0";
        final String expirationTimeNegative = "-1";
        final int expectedValue = 3600;

        when(appConfig.getExpiration()).thenReturn(expirationTimeZero).thenReturn(expirationTimeNegative);

        Assertions.assertEquals(expectedValue, appConfigService.getTokenExpiration());
        Assertions.assertEquals(expectedValue, appConfigService.getTokenExpiration());
    }

    @Test
    public void getTokenExpirationCallsAppConfigGetExpirationFunction(){
        final String expirationTime = "420";

        when(appConfig.getExpiration()).thenReturn(expirationTime);

        appConfigService.getTokenExpiration();

        verify(appConfig).getExpiration();
    }
}
