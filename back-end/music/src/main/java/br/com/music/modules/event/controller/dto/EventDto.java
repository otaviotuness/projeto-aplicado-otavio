package br.com.music.modules.event.controller.dto;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.song.controller.dto.SongDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

  private Integer id;

  @NotBlank private String date;

  @NotBlank private String time;

  @NotBlank private String description;

  @NotNull
  @Min(value = 0)
  private BigDecimal value = BigDecimal.ZERO;

  @JsonProperty("id_user")
  @NotNull
  private Integer idUser;

  @JsonProperty("id_user_master")
  @NotNull
  private Integer idUserMaster;

  private ReceiveDto receive;
  private Set<SongDto> songs;
}
