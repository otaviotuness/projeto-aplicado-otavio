package br.com.music.modules.receive.controller.mapper;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.dto.ReceiveItemDto;
import br.com.music.modules.receive.controller.dto.ReceiveResponseDto;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

  ReceiveDomain toDomain(ReceiveDto receiveDto);

  ReceiveItemDomain mapReceiveItem(ReceiveItemDto item);

  ReceiveResponseDto toResponseDto(ReceiveDomain receiveDomain);

  List<ReceiveResponseDto> toListResponseDto(List<ReceiveDomain> receiveDomain);

  ReceiveResponseDto.ReceiveItemResponseDto mapReceiveItemDto(ReceiveItemDto item);
}
