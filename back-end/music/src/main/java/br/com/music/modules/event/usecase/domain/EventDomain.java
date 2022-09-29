package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer description;
  private String date;

  //  @ManyToMany private Set<SongDomain> songsDomain;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "receive_id")
  private ReceiveDomain receiveDomain;

  //  @OneToMany private List<Checklist> checklist;
}
