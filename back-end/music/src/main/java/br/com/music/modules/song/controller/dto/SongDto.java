package br.com.music.modules.song.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SongDto {

  private Integer id;

  @NotBlank private String description;

  @NotBlank private String link;

  @JsonProperty("id_user")
  private int idUser;
}
