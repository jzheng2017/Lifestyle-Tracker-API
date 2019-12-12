package webservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.configs.KeyConfig;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class KeyService {

    private KeyConfig keyConfig;

    @Autowired
    public void setKeyConfig(KeyConfig keyConfig) {
        this.keyConfig = keyConfig;
    }

    public String ConvertSecretKeyToString(Key secretKey){
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public Key ConvertStringToSecretKey(String secretKey){
        return (Key) new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    }

    public Key getSecretKey(){
        return ConvertStringToSecretKey(keyConfig.getSecret());
    }

}
