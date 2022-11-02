package br.com.music.modules.commum.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {

  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;
}
