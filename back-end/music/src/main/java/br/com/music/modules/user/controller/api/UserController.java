package br.com.music.modules.user.controller.api;

import static br.com.music.modules.commum.anotattion.TypePermissions.ADMIN_MUSICIAN;
import static br.com.music.modules.commum.anotattion.TypePermissions.ALL;
import static br.com.music.modules.user.enumeration.RoleEnum.MUSICIAN;

import br.com.music.modules.commum.anotattion.Permission;
import br.com.music.modules.user.controller.UserAPI;
import br.com.music.modules.user.controller.dto.NewUserDto;
import br.com.music.modules.user.controller.dto.UserDto;
import br.com.music.modules.user.controller.dto.UserResponseDto;
import br.com.music.modules.user.controller.mapper.UsuarioMapper;
import br.com.music.modules.user.domain.RoleDomain;
import br.com.music.modules.user.domain.UserDomain;
import br.com.music.modules.user.usecase.UserUseCase;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

  final UsuarioMapper usuarioMapper;
  final UserUseCase userUseCase;

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id) {

    var user = userUseCase.findById(id);

    return ResponseEntity.ok(user);
  }

  @Permission(permissions = ALL)
  public ResponseEntity<UserResponseDto> me(Principal principal) {
    var user = userUseCase.findByEmail(principal.getName());

    final var resp =
        UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .roles(
                Set.of(user.getRoles().stream().map(RoleDomain::getRoleName).findAny().orElse("")))
            .build();

    return ResponseEntity.ok(resp);
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<List<UserDomain>> findAll(Authentication authentication) {

    List<UserDomain> usuarios = userUseCase.findAll();

    return ResponseEntity.ok(usuarios);
  }

  @Permission(permissions = ALL)
  public ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);
    final var role = new RoleDomain(MUSICIAN.getId(), MUSICIAN.getRoleName());
    userDomain.setRoles(Set.of(role));

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {

    userUseCase.deleteUserById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
