package br.com.music.modules.event.controller.dto;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.song.controller.dto.SongDto;
import java.util.Set;
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

  @NotBlank private String description;

  private ReceiveDto receive;
  private Set<SongDto> songs;
}
