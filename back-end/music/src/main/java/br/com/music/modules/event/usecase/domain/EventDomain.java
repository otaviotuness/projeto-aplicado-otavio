package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDomain {

  private Integer id;
  private Integer description;
  private String date;
  private Set<SongDomain> songsDomain = new HashSet<>();
}
