package br.com.music.modules.user.entrypoint;

import static br.com.music.modules.user.enumeration.RoleEnum.ADMIN;
import static br.com.music.modules.user.enumeration.RoleEnum.MUSICIAN;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.entrypoint.mapper.UsuarioMapper;
import br.com.music.modules.user.usecase.UserUseCase;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.domain.UserDomain;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserDetailsService {

  private final UsuarioMapper usuarioMapper;
  private final UserUseCase userUseCase;
  private final UserInfo userInfo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    final var user = userUseCase.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException("Email not found");
    } else {
      final var role = user.getRoles().stream().map(RoleDomain::getRoleName).findAny().orElse("");

      userInfo.setName(user.getName());
      userInfo.setEmail(user.getEmail());
      userInfo.setRoles(user.getRoles());
      userInfo.setUserId(user.getId());
      userInfo.setUserIdMaster(user.getId_master() == null ? 0 : user.getId_master());
      userInfo.setRole(role);
      userInfo.setAdmin(role.equals(ADMIN.getRoleName()));
    }

    return user;
  }

  @GetMapping("/login")
  private UserResponseDto login() {

    return usuarioMapper.toResponse(userInfo);
  }

  @GetMapping("/user/{id}")
  private ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id) {

    var usuario = userUseCase.findById(id);

    return ResponseEntity.ok(usuario);
  }

  @GetMapping("/users")
  private ResponseEntity<List<UserDomain>> findAll() {

    List<UserDomain> usuarios = userUseCase.findAll();

    return ResponseEntity.ok(usuarios);
  }

  @PostMapping("/newUser")
  private ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

    final var role = new RoleDomain(MUSICIAN.getId(), MUSICIAN.getRoleName());

    userDomain.setRoles(Set.of(role));

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @PostMapping("/user")
  private ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

    userUseCase.saveUser(userDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/user/{id}")
  private ResponseEntity<String> deleteUserById(@PathVariable Integer id) {

    userUseCase.deleteUserById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
