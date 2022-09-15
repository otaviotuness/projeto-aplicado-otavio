package br.com.music.modules.commum.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class UserInfo {

  private int userId;
  private int userIdMaster;
  private String role;
  private boolean isAdmin;
}
