package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class EventDomain {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;
  private String date;
  private String time;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "event_song",
      joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "song_id"))
  private Set<SongDomain> songs = new HashSet<>();

  @OneToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "receive_id", referencedColumnName = "id")
  private ReceiveDomain receive;

  //  @OneToMany private List<Checklist> checklist;
}
