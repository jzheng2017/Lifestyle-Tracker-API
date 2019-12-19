package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webservice.configs.AppConfig;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyServiceTest {
    @InjectMocks
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

    }
}
