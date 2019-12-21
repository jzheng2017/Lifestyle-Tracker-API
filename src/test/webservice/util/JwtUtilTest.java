package webservice.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webservice.services.AppConfigService;
import webservice.services.KeyService;

public class JwtUtilTest {
    @InjectMocks
    private JwtUtil jwtUtil;
    @Mock
    private KeyService keyService;
    @Mock
    private AppConfigService appConfigService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

}
