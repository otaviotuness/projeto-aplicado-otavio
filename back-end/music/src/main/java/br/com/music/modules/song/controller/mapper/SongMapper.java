package br.com.music.modules.song.controller.mapper;

import br.com.music.modules.song.controller.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDomain toDomain(SongDto songDto);

}
