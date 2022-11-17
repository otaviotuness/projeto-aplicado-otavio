package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.user.entrypoint.dto.UserResponseDto;
import br.com.music.modules.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TokenController {

  private final UserUseCase userUseCase;
  private final UserInfo userInfo;

  @PermitAll
  @GetMapping("/token")
  public ResponseEntity<UserResponseDto> login(Authentication authentication) {
    String token = userUseCase.generateToken(authentication);
    return ResponseEntity.ok().body(generateToken(token));
  }

  @PermitAll
  @PostMapping("/refreshToken")
  public ResponseEntity<UserResponseDto> refresh(Authentication authentication) {
    String token = userUseCase.refreshToken(authentication);
    return ResponseEntity.ok().body(generateToken(token));
  }

  private UserResponseDto generateToken(final String token){
    return UserResponseDto.builder()
            .id(userInfo.getUserId())
            .email(userInfo.getEmail())
            .name(userInfo.getName())
            .token(token)
            .roles(Set.of(userInfo.getRole()))
            .build();
  }
}
