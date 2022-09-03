package br.com.music.modules.user.entrypoint;

import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.mapper.UsuarioMapper;
import br.com.music.modules.user.usecase.UserUseCase;
import br.com.music.modules.user.usecase.domain.UserDomain;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UsuarioMapper usuarioMapper;
  private final UserUseCase userUseCase;

  @GetMapping("/user/{id}")
  private ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id) {

    var usuario = userUseCase.findById(id);

    return ResponseEntity.ok(Optional.of(usuario));
  }

  @GetMapping("/users")
  private ResponseEntity<List<UserDomain>> findAll() {

    List<UserDomain> usuarios = userUseCase.findAll();

    return ResponseEntity.ok(usuarios);
  }

  @PostMapping("/newUser")
  private ResponseEntity<String> createNewUser(@Valid @RequestBody UserDto userDto) {

    var userDomain = usuarioMapper.toDomain(userDto);

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
