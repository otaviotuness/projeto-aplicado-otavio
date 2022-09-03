package br.com.music.modules.user.entrypoint.mapper;

import br.com.music.modules.user.entrypoint.dto.RoleDto;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleDomain toDomain(RoleDto roleDto);
}
