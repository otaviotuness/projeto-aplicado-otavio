package br.com.music.modules.user.entrypoint.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDto {

  private String name;
  private String email;
  private Set<RoleResponse> roles = new HashSet<>();
  private String token;

  @Data
  public static class RoleResponse {
    private String roleName;
  }
}
