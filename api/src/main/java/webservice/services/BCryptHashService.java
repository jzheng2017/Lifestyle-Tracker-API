package webservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webservice.services.interfaces.HashService;

@Service
public class BCryptHashService implements HashService {


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String plain) {
        return passwordEncoder.encode(plain);
    }

    @Override
    public boolean valid(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }


}
