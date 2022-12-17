package br.com.music.modules.checklist.controller.mapper;

import br.com.music.modules.checklist.controller.dto.ChecklistDto;
import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistMapper {

  ChecklistDomain toDomain(ChecklistDto checklistDto);
}
