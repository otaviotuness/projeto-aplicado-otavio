package br.com.music.modules.receive.controller.mapper;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.dto.ReceiveItemDto;
import br.com.music.modules.receive.usecase.domain.Receive;
import br.com.music.modules.receive.usecase.domain.ReceiveItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

  Receive toDomain(ReceiveDto receiveDto);

  ReceiveItem mapReceiveItem(ReceiveItemDto item);
}
