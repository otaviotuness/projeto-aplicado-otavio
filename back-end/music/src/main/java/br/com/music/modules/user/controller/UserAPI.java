package br.com.music.modules.user.controller;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.user.controller.dto.NewUserDto;
import br.com.music.modules.user.controller.dto.UserDto;
import br.com.music.modules.user.controller.dto.UserResponseDto;
import br.com.music.modules.user.domain.UserDomain;
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

@PermitAll
public interface UserAPI {

  @GetMapping("/user/{id}")
  ResponseEntity<Optional<UserDomain>> findById(@PathVariable Integer id);

  @GetMapping("/me")
  ResponseEntity<UserResponseDto> me(Principal principal);

  @GetMapping("/users")
  ResponseEntity<List<UserDomain>> findAll(Authentication authentication);

  @PostMapping("/newUser")
  ResponseEntity<String> createNewUser(@Valid @RequestBody NewUserDto userDto);

  @PostMapping("/user")
  ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto);

  @DeleteMapping("/user/{id}")
  ResponseEntity<String> deleteUserById(@PathVariable Integer id);
}
