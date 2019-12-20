package webservice.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BCryptHashServiceTest {
    @InjectMocks
    private BCryptHashService hashService;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void encodeReturnsEncodedString() {
        final String plain = "plain";
        final String encoded = "encoded";

        when(passwordEncoder.encode(plain)).thenReturn(encoded);

        Assertions.assertEquals(encoded, hashService.encode(plain));
    }

    @Test
    public void encodedCallsPasswordEncoderEncodeFunction() {
        final String plain = "plain";
        hashService.encode(plain);
        verify(passwordEncoder).encode(plain);
    }

    @Test
    public void validReturnsTrueIfTwoStringMatches() {
        final String plain = "plain";
        final String encoded = "encoded";

        when(passwordEncoder.matches(plain, encoded)).thenReturn(true);

        Assertions.assertTrue(hashService.valid(plain, encoded));
    }

    @Test
    public void validReturnsFalseIfTwoStringDoNotMatch() {
        final String plain = "plain";
        final String encoded = "encoded";

        when(passwordEncoder.matches(plain, encoded)).thenReturn(false);

        Assertions.assertFalse(hashService.valid(plain, encoded));
    }

    @Test
    public void validCallsPasswordEncoderMatchesFunction() {
        final String plain = "plain";
        final String encoded = "encoded";

        hashService.valid(plain, encoded);
        verify(passwordEncoder).matches(plain, encoded);
    }
}
