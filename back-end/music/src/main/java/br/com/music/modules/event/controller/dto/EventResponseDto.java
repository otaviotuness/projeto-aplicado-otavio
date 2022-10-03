package br.com.music.modules.event.controller.dto;

import br.com.music.modules.song.controller.dto.SongDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventResponseDto {

  private Integer id;
  private String date;
  private String time;
  private String description;
  private BigDecimal value = BigDecimal.ZERO;

  @JsonProperty("id_user")
  @NotNull
  private Integer idUser;

  @JsonProperty("id_user_master")
  @NotNull
  private Integer idUserMaster;

  private ReceiveResponseEventDto receive;
  private Set<SongDto> songs;

  @Data
  public static class ReceiveResponseEventDto {
    private Integer id;
    private BigDecimal totalValue;
    private BigDecimal totalValueReceive;
  }
}
