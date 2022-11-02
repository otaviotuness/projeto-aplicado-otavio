package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.PermitAdmin;
import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.commum.anotattion.PermitMusician;
import br.com.music.modules.commum.anotattion.PermitOperator;
import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.usecase.domain.UserDomain;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface User {

  @PermitMusician
  @PermitAdmin
  @GetMapping("/user/{id}")
  ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id);

  @PermitMusician
  @PermitOperator
  @PermitAdmin
  @GetMapping("/me")
  ResponseEntity<UserResponseDto> me(Principal principal);

  @PermitMusician
  @PermitAdmin
  @GetMapping("/users")
  ResponseEntity<List<UserDomain>> findAll();

  @PermitAll
  @PostMapping("/newUser")
  ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto);

  @PermitMusician
  @PermitAdmin
  @PostMapping("/user")
  ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto);

  @PermitAdmin
  @DeleteMapping("/user/{id}")
  ResponseEntity<String> deleteUserById(@PathVariable Integer id);
}
