package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import webservice.configs.AppConfig;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KeyServiceTest {
    @InjectMocks
    @Spy
    private KeyService keyService;
    @Mock
    private AppConfig appConfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ConvertSecretKeyToStringReturnsString() {
        final byte[] bytes = {4, 2, 0};
        final Key secretKey = new SecretKeySpec(bytes, "HmacSHA256");

        Assertions.assertNotNull(keyService.ConvertSecretKeyToString(secretKey));
    }

    @Test
    public void ConvertStringToSecretKeyReturnsSecretKey() {
        final String secretKey = "verySecretKey";

        Assertions.assertNotNull(keyService.ConvertStringToSecretKey(secretKey));
    }

    @Test
    public void getSecretKeyReturnsSecretKey(){
        final String secretKey = "verySecretKey";

        when(appConfig.getSecret()).thenReturn(secretKey);

        Assertions.assertNotNull(keyService.getSecretKey());
    }

    @Test
    public void getSecretKeyCallsAppConfigGetSecretKey(){
        final String secretKey = "verySecretKey";

        when(appConfig.getSecret()).thenReturn(secretKey);

        keyService.getSecretKey();

        verify(appConfig).getSecret();
    }

    @Test
    public void getSecretKeyCallsConvertStringToSecretKeyFunction(){
        final String secretKey = "verySecretKey";

        when(appConfig.getSecret()).thenReturn(secretKey);

        keyService.getSecretKey();

        verify(keyService).ConvertStringToSecretKey(anyString());
    }
}
