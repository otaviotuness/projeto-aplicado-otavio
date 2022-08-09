package br.com.music.modules.event.entrypoint.dto;

import br.com.music.modules.song.usecase.domain.SongDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    @NotBlank
    private Integer id;

    @NotBlank
    private String date;

    @NotBlank
    private String time;

    @NotEmpty
    private List<SongDomain> event_song = new ArrayList<>();
}
