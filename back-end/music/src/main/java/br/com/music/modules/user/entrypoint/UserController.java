package br.com.music.modules.user.entrypoint;

import static br.com.music.modules.commum.anotattion.TypePermissions.*;
import static br.com.music.modules.user.enumeration.RoleEnum.MUSICIAN;

import br.com.music.modules.commum.anotattion.Permission;
import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.entrypoint.mapper.UsuarioMapper;
import br.com.music.modules.user.usecase.UserUseCase;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.domain.UserDomain;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController implements User {

  final UsuarioMapper usuarioMapper;
  final UserUseCase userUseCase;

  public ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id) {

    var user = userUseCase.findById(id);

    return ResponseEntity.ok(user);
  }

  @Permission(permissions = ALL)
  public ResponseEntity<UserResponseDto> me(Principal principal) {
    var user = userUseCase.findByEmail(principal.getName());

    final var resp = UserResponseDto.builder()
            .email(user.getEmail())
            .name(user.getName())
            .roles(Set.of(user.getRoles().stream().map(RoleDomain::getRoleName).findAny().orElse("")))
            .build();

    return ResponseEntity.ok(resp);
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<List<UserDomain>> findAll(Authentication authentication) {

    List<UserDomain> usuarios = userUseCase.findAll();

    return ResponseEntity.ok(usuarios);
  }

  public ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

    final var role = new RoleDomain(MUSICIAN.getId(), MUSICIAN.getRoleName());

    userDomain.setRoles(Set.of(role));

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {

    userUseCase.deleteUserById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
