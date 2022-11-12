package br.com.music.modules.user.dataprovider.database;

import br.com.music.modules.user.dataprovider.repository.UserRepository;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.domain.UserDomain;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import static br.com.music.modules.user.enumeration.RoleEnum.OPERATOR;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMySQLDataProvider implements UserDadosGateway {

  private final UserRepository userRepository;

  @Override
  public void saveUser(UserDomain userDomain) {
    log.info("Save user in DB.");

    if(userDomain.getRoles().isEmpty()){
      userDomain.setRoles(Set.of(new RoleDomain(3, OPERATOR.getRoleName())));
    }

    userRepository.save(userDomain);

    log.info("Save user ind DB successfully!");
  }

  @Override
  public List<UserDomain> findAll() {
    log.info("Search users.");

    List<UserDomain> usuarios = userRepository.findAll();

    log.info("Search users successfully.");

    return usuarios;
  }

  @Override
  public Optional<UserDomain> findById(Integer id) {
    log.info("Search users.");

    var user = userRepository.findById(id);

    log.info("Search users successfully.");

    return user;
  }

  @Override
  public void deleteUserById(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserDomain findByEmail(String email) {
    log.info("Search users.");

    var user = userRepository.findByEmail(email);

    log.info("Search users successfully.");

    return user;
  }
}
