package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.checklist.controller.Checklist;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// @Entity
// @Table(name = "event")
public class EventDomain {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer description;
  private String date;

  @ManyToMany private Set<SongDomain> songsDomain;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "receive_id", referencedColumnName = "id")
  private ReceiveDomain receiveDomain;

  @OneToMany private List<Checklist> checklist;
}
