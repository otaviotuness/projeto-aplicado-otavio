package br.com.music.modules.checklist.entrypoint.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChecklistDto {

  @NotNull private Integer id;

  @NotBlank private String description;

  @NotNull private Integer idUser;
}
