package com.fun.auth.config.properties;

import com.fun.auth.utils.RsaUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * rsa 参数配置
 *
 * @author MrDJun 2020/10/1
 */
@Getter
@Setter
// 系统启动时直接载入 IOC 容器
@SpringBootConfiguration
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {
    /**
     * 公钥存放路径
     */
    private String publicKeyPath;

    /**
     * 私钥存放路径
     */
    private String privateKeyPath;

    /**
     * 私钥对象
     */
    private PrivateKey privateKey;

    /**
     * 公钥对象
     */
    private PublicKey publicKey;

    @PostConstruct
    public void createRsaKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
        privateKey = RsaUtils.getPrivateKey(privateKeyPath);
    }
}
