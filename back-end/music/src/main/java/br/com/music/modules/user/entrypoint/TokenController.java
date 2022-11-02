package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.entrypoint.mapper.UsuarioMapper;
import br.com.music.modules.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TokenController {

  private final UsuarioMapper usuarioMapper;
  private final UserUseCase userUseCase;
  private final UserInfo userInfo;

  @PermitAll
  @GetMapping("/token")
  public ResponseEntity<UserResponseDto> login(Authentication authentication) {

    String token = userUseCase.generateToken(authentication);

    return ResponseEntity.ok().body(usuarioMapper.toResponse(userInfo, token));
  }
}