package br.com.music.modules.checklist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChecklistDto {

  private Integer id;

  @NotBlank private String description;

  @JsonProperty("id_user")
  private Integer idUser;

  private Boolean value;
}
