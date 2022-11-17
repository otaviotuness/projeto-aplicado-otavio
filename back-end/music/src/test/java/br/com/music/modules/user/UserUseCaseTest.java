package br.com.music.modules.user;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.music.modules.user.domain.UserDomain;
import br.com.music.modules.user.usecase.UserUseCase;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

  private static final Integer USER_ID = 123;

  @InjectMocks UserUseCase userUseCase;

  @Mock private UserDadosGateway userDadosGateway;
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private JwtEncoder encoder;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(userDadosGateway, passwordEncoder, encoder);
  }

  @Test
  void givenSaveUser_thenReturnSuccessfully() {
    final var user = EASY_RANDOM.nextObject(UserDomain.class);

    userUseCase.saveUser(user);

    verify(userDadosGateway, times(1)).saveUser(user);
  }

  @Test
  void givenFindAll_thenReturnSuccessfully() {
    final var user = EASY_RANDOM.nextObject(UserDomain.class);

    when(userDadosGateway.findAll()).thenReturn(List.of(user));

    userUseCase.findAll();

    verify(userDadosGateway, times(1)).findAll();
  }

  @Test
  void givenFindById_thenReturnSuccessfully() {
    final var user = EASY_RANDOM.nextObject(UserDomain.class);

    when(userDadosGateway.findById(USER_ID)).thenReturn(Optional.of(user));

    userUseCase.findById(USER_ID);

    verify(userDadosGateway, times(1)).findById(USER_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    userUseCase.deleteUserById(USER_ID);

    verify(userDadosGateway, times(1)).deleteUserById(USER_ID);
  }

  // TODO fazer testes
  @Test
  void givenGenerateToken_thenReturnSuccessfully() {}

  // TODO fazer testes
  @Test
  void givenRefreshToken_thenReturnSuccessfully() {}
}
