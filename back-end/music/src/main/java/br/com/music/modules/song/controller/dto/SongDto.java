package br.com.music.modules.song.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SongDto {

    @NotBlank
    private String description;

    @NotBlank
    private String link;

    @NotNull
    @JsonProperty("id_user")
    private int idUser;
}
