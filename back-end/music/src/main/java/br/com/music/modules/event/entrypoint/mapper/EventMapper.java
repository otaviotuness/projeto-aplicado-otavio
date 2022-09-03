package br.com.music.modules.event.entrypoint.mapper;

import br.com.music.modules.event.entrypoint.dto.EventDto;
import br.com.music.modules.event.usecase.domain.EventDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

  EventDomain toDomain(EventDto eventDto);
}
