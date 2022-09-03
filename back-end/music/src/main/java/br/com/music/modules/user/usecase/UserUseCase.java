package br.com.music.modules.user.usecase;

import br.com.music.modules.user.usecase.domain.UserDomain;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUseCase {

  private final UserDadosGateway userDadosGateway;

  public void saveUser(UserDomain userDomain) {
    userDadosGateway.saveUser(userDomain);
  }

  public List<UserDomain> findAll() {
    return userDadosGateway.findAll();
  }

  public UserDomain findById(Integer id) {
    return userDadosGateway.findById(id);
  }

  public void deleteUserById(Integer id) {
    userDadosGateway.deleteUserById(id);
  }
}
