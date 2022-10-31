package br.com.music.modules.commum.utils.jwt;

import static java.util.Objects.isNull;

import br.com.music.modules.commum.exceptions.BadRequestException;
import br.com.music.modules.commum.exceptions.GlobalException;
import br.com.music.modules.commum.exceptions.Issue;
import br.com.music.modules.commum.exceptions.IssueType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  private static final JsonMapper jsonMapper =
      JsonMapper.builder()
          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
          .addModule(new JavaTimeModule())
          .build();

  public static JwtObjectImpl decodeJwt(String accessToken) {
    JwtObjectImpl jwtObject;
    String[] tokens = accessToken.split("\\.");

    //    if (tokens.length != 3) {
    //      throw new BadRequestException(new Issue(IssueType.BAD_REQUEST_INVALID_JWT));
    //    }

    byte[] claimsBytesDecoded =
        Base64.getDecoder().decode(tokens[1].getBytes(StandardCharsets.UTF_8));
    jwtObject = convertByteToJwtObject(claimsBytesDecoded);

    return jwtObject;
  }

  public static JwtObjectImpl convertByteToJwtObject(byte[] bytes) {
    JwtObjectImpl jwtObject;
    try {
      jwtObject = jsonMapper.readValue(bytes, JwtObjectImpl.class);
      if (isNull(jwtObject.email)) {
        throw new BadRequestException(
            new Issue(IssueType.BAD_REQUEST_AUTHORIZATION_TOKEN_INVALID_OR_EXPIRED));
      }

    } catch (IOException e) {
      logger.error("Could not convert token to object", e);
      throw GlobalException.globalException();
    }
    return jwtObject;
  }

  public interface JwtObject {
    String getEmail();
  }

  @Getter
  public static class JwtObjectImpl implements JwtObject {

    @JsonProperty("user_name")
    private String email;
  }
}
