package br.com.music.modules.user.controller.mapper;

import br.com.music.modules.user.controller.dto.RoleDto;
import br.com.music.modules.user.domain.RoleDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleDomain toDomain(RoleDto roleDto);
}
