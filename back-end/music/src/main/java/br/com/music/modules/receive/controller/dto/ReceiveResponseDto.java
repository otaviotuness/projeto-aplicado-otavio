package br.com.music.modules.receive.controller.dto;

import br.com.music.modules.receive.usecase.domain.TypeReceive;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveResponseDto {

  private Integer id;
  private String description;

  @JsonProperty("id_user")
  private Integer idUser;

  @JsonProperty("total_value")
  private Integer totalValue;

  @JsonProperty("total_value_receive")
  private BigDecimal totalValueReceive = BigDecimal.ZERO;

  private List<ReceiveItemResponseDto> items = new ArrayList<>();

  @Data
  public static class ReceiveItemResponseDto {

    private String description;
    private ReceiveDto receive;
    private BigDecimal value;

    @JsonProperty("type_receive")
    private TypeReceive typeReceive;
  }
}
