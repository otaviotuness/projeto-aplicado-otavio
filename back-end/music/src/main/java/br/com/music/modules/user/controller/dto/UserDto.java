package br.com.music.modules.user.controller.dto;

import br.com.music.modules.user.domain.RoleDomain;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class UserDto {

  @NotBlank(message = "Name is not blank")
  private String name;

  @NotBlank(message = "Email is not blank")
  private String email;

  @NotBlank(message = "Telephone is not blank")
  private String telephone;

  private String password;

  private Set<RoleDomain> roles = new HashSet<>();
}
