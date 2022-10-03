package br.com.music.modules.checklist.usecase.domain;

import br.com.music.modules.event.usecase.domain.EventDomain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "checklist")
public class ChecklistDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  @JsonProperty("id_user")
  private Integer idUser;

  private Boolean value = false;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "event_id")
  private EventDomain event;
}
