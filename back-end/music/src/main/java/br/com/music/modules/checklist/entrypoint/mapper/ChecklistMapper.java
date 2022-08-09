package br.com.music.modules.checklist.entrypoint.mapper;

import br.com.music.modules.checklist.entrypoint.dto.ChecklistDto;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistMapper {

    ChecklistDomain toDomain(ChecklistDto checklistDto);

}
