package br.com.music.modules.user.entrypoint.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class UserResponseDto {

  private String name;
  private String email;
  private Set<RoleResponse> roles = new HashSet<>();

  @Data
  public static class RoleResponse {
    private String roleName;
  }
}
