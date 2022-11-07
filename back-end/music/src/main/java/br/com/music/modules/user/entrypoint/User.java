package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.*;
import br.com.music.modules.user.entrypoint.dto.NewUserDto;
import br.com.music.modules.user.entrypoint.dto.UserDto;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.usecase.domain.UserDomain;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface User {

  @GetMapping("/user/{id}")
  ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id);

  @PermitAll
  @GetMapping("/me")
  ResponseEntity<UserResponseDto> me(Principal principal);


  @PermitAll
  @GetMapping("/users")
  ResponseEntity<List<UserDomain>> findAll(Authentication authentication);

  @PermitAll
  @PostMapping("/newUser")
  ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto);

  @PostMapping("/user")
  ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto);

  @DeleteMapping("/user/{id}")
  ResponseEntity<String> deleteUserById(@PathVariable Integer id);
}
