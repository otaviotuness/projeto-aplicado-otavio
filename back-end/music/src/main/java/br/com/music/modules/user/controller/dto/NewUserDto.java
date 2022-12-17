package br.com.music.modules.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class NewUserDto {

  @NotBlank(message = "Name is not blanck")
  private String name;

  @NotBlank(message = "Email is not blanck")
  private String email;

  @NotBlank(message = "Telephone is not blanck")
  private String telephone;

  @NotBlank(message = "Password is not blanck")
  private String password;

  @NotBlank(message = "IdMaster is not blanck")
  @JsonProperty("id_master")
  private String idMaster;
}
