package br.com.music.modules.receive.entrypoint.mapper;

import br.com.music.modules.receive.entrypoint.dto.ReceiveDto;
import br.com.music.modules.receive.entrypoint.dto.ReceiveItemDto;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

    ReceiveDomain toDomain(ReceiveDto receiveDto);

    ReceiveItemDomain mapReceiveItem(ReceiveItemDto item);

}
