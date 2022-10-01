package br.com.music.modules.receive.usecase.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "receive_item")
public class ReceiveItemDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;
  private BigDecimal value = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  private TypeReceive typeReceive;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "receive_id")
  private ReceiveDomain receiveDomain;
}
