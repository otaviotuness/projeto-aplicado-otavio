package br.com.music.modules.event.entrypoint.dto;

import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

  @NotBlank private Integer id;

  @NotBlank private String date;

  @NotBlank private String time;

  @NotEmpty private List<SongDomain> event_song = new ArrayList<>();
}
