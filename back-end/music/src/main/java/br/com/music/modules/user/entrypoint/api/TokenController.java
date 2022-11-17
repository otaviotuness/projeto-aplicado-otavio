package br.com.music.modules.user.entrypoint.api;

import static br.com.music.modules.commum.anotattion.TypePermissions.ALL;

import br.com.music.modules.commum.anotattion.Permission;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.entrypoint.TokenAPI;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.usecase.UserUseCase;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController implements TokenAPI {

  private final UserUseCase userUseCase;
  private final UserInfo userInfo;

  public ResponseEntity<UserResponseDto> login(Authentication authentication) {
    String token = userUseCase.generateToken(authentication);
    return ResponseEntity.ok().body(generateToken(token));
  }

  @Permission(permissions = ALL)
  public ResponseEntity<UserResponseDto> refresh(Authentication authentication) {
    String token = userUseCase.refreshToken(authentication);
    return ResponseEntity.ok().body(generateToken(token));
  }

  private UserResponseDto generateToken(final String token) {
    return UserResponseDto.builder()
        .id(userInfo.getUserId())
        .email(userInfo.getEmail())
        .name(userInfo.getName())
        .token(token)
        .roles(Set.of(userInfo.getRole()))
        .build();
  }
}
