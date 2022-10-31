package br.com.music.modules.user.usecase;

import br.com.music.modules.user.usecase.domain.UserDomain;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUseCase {

  private final UserDadosGateway userDadosGateway;
  private final PasswordEncoder passwordEncoder;

  public void saveUser(UserDomain userDomain) {
    // encrypt password
    userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));

    userDadosGateway.saveUser(userDomain);
  }

  public List<UserDomain> findAll() {
    return userDadosGateway.findAll();
  }

  public Optional<UserDomain> findById(Integer id) {
    return userDadosGateway.findById(id);
  }

  public void deleteUserById(Integer id) {
    userDadosGateway.deleteUserById(id);
  }

  public UserDomain findByEmail(String email) {
    return userDadosGateway.findByEmail(email);
  }
}
