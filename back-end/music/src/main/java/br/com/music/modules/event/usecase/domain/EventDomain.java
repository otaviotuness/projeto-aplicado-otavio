package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.checklist.controller.Checklist;
import br.com.music.modules.receive.usecase.domain.Receive;
import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventDomain {

  private Integer id;
  private Integer description;
  private String date;

  @ManyToMany private Set<SongDomain> songsDomain;

  @OneToOne private Receive receive;

  @OneToMany private List<Checklist> checklist;
}
