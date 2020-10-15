package com.fun.client.config;

import com.fun.client.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@Data
@SpringBootConfiguration
@ConfigurationProperties("rsa.key")
public class PublicKeyProperties {

    private String publicKeyPath;

    private PublicKey publicKey;

    @PostConstruct
    public void createRsaKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
    }
}
