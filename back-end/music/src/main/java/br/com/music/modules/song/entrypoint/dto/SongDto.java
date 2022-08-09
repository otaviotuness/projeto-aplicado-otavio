package br.com.music.modules.song.entrypoint.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SongDto {

    @NotNull
    private Integer id;

    @NotBlank
    private String description;

    @NotBlank
    private String link;

    @NotNull
    private Integer idUser;
}
