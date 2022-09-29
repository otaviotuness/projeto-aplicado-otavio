package br.com.music.modules.event.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

  private Integer id;

  @NotBlank private String date;

  @NotBlank private String time;

  // @NotEmpty private List<SongDomain> event_song = new ArrayList<>();
}
