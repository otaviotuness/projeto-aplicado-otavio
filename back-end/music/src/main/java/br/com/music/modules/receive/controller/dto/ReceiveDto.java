package br.com.music.modules.receive.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiveDto {

  @NotBlank private String description;

  @NotNull
  @JsonProperty("id_user")
  private Integer idUser;

  @NotNull
  @JsonProperty("total_value")
  private Integer totalValue;

  @NotNull
  @JsonProperty("total_value_receive")
  private Integer totalValueReceive;

  @NotEmpty
  @JsonProperty("items")
  private List<ReceiveItemDto> receiveItem = new ArrayList<>();
}
