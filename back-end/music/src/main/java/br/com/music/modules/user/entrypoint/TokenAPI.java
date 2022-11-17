package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@PermitAll
public interface TokenAPI {

  @GetMapping("/token")
  ResponseEntity<UserResponseDto> login(Authentication authentication);

  @PermitAll
  @PostMapping("/refreshToken")
  ResponseEntity<UserResponseDto> refresh(Authentication authentication);
}
