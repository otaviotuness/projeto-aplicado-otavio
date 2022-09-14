package br.com.music.modules.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
public class UserInfo {

  private Integer userId;
  private Integer userIdMaster;
  private String role;
}
