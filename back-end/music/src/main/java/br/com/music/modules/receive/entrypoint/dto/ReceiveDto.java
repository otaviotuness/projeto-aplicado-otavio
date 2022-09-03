package br.com.music.modules.receive.entrypoint.dto;

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

  @NotNull private Integer idUser;

  @NotNull private Integer totalValue;

  @NotNull private Integer totalValueReceive;

  @NotEmpty private List<ReceiveItemDto> receiveItem = new ArrayList<>();
}
