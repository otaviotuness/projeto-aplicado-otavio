package br.com.music.modules.receive.entrypoint.mapper;

import br.com.music.modules.receive.entrypoint.dto.ReceiveDto;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.song.entrypoint.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

    ReceiveDomain toDomain(ReceiveDto receiveDto);

}
