package br.com.music.modules.checklist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChecklistDto {

  @NotBlank private String description;

  @NotNull
  @JsonProperty("id_user")
  private int idUser;
}
