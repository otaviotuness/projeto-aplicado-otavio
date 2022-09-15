package br.com.music.modules.user.entrypoint.mapper;

import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.usecase.domain.UserDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UserDomain toDomain(UserDto userDto);

  UserDomain toDomain(NewUserDto userDto);
}
