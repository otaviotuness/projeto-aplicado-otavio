package br.com.music.modules.receive.entrypoint.dto;

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

  @NotNull private Integer idReceive;

  @NotNull private double value;

  @NotNull private Integer idTypeReceive;
}
