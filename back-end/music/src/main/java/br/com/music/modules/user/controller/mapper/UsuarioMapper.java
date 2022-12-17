package br.com.music.modules.user.controller.mapper;

import br.com.music.modules.user.controller.dto.NewUserDto;
import br.com.music.modules.user.controller.dto.UserDto;
import br.com.music.modules.user.domain.UserDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UserDomain toDomain(UserDto userDto);

  UserDomain toDomain(NewUserDto userDto);
}
