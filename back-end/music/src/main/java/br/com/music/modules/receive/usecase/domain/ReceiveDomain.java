package br.com.music.modules.receive.usecase.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "receive")
public class ReceiveDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;
  private Integer idUser;
  private BigDecimal totalValue = BigDecimal.ZERO;
  private BigDecimal totalValueReceive = BigDecimal.ZERO;

  @JsonManagedReference
  @OneToMany(mappedBy = "receiveDomain")
  @Fetch(FetchMode.JOIN)
  private List<ReceiveItemDomain> items = new ArrayList<>();

  //  @JsonBackReference
  //  @OneToOne(mappedBy = "receive")
  //  private EventDomain event;
}
