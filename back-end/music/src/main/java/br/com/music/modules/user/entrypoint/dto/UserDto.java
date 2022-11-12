package br.com.music.modules.user.entrypoint.dto;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class UserDto {

  @NotBlank(message = "Name is not blanck")
  private String name;

  @NotBlank(message = "Email is not blanck")
  private String email;

  @NotBlank(message = "Telephone is not blanck")
  private String telephone;

  @NotBlank(message = "Password is not blanck")
  private String password;

  private Set<RoleDomain> roles = new HashSet<>();
}
