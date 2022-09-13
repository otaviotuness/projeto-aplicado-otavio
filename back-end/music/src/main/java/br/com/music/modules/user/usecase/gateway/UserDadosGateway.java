package br.com.music.modules.user.usecase.gateway;

import br.com.music.modules.user.usecase.domain.UserDomain;
import java.util.List;
import java.util.Optional;

public interface UserDadosGateway {

  void saveUser(UserDomain userDomain);

  List<UserDomain> findAll();

  Optional<UserDomain> findById(Integer id);

  void deleteUserById(Integer id);

  UserDomain findByEmail(String email);
}
