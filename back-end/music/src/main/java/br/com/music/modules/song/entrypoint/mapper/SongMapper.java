package br.com.music.modules.song.entrypoint.mapper;

import br.com.music.modules.song.entrypoint.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDomain toDomain(SongDto songDto);

}
