package br.com.music.modules.receive.controller.mapper;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.dto.ReceiveItemDto;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

  ReceiveDomain toDomain(ReceiveDto receiveDto);

  ReceiveItemDomain mapReceiveItem(ReceiveItemDto item);
}
