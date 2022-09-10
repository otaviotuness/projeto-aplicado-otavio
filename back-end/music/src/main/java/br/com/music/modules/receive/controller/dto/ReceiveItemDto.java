package br.com.music.modules.receive.controller.dto;

import br.com.music.modules.receive.usecase.domain.TypeReceive;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiveItemDto {

  @NotNull private Integer id;
  @NotBlank private String description;

  @NotNull private ReceiveDto receive;

  @NotNull private double value;

  @JsonProperty("type_receive")
  @NotNull
  private TypeReceive typeReceive;
}
