package br.com.music.modules.commum.utils;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class UserInfo {

  private String name;
  private String email;
  private Set<RoleDomain> roles;
  private int userId;
  private int userIdMaster;
  private String role;
  private boolean isAdmin;
}
