package br.com.music.modules.user.entrypoint.mapper;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.usecase.domain.UserDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UserDomain toDomain(UserDto userDto);

  UserDomain toDomain(NewUserDto userDto);

  @Mapping(source = "token", target = "token")
  UserResponseDto toResponse(UserInfo userInfo, String token);

  UserResponseDto toResponse(UserDomain user);
}
