package br.com.music.modules.user.usecase.gateway;

import br.com.music.modules.user.usecase.domain.UserDomain;
import java.util.List;

public interface UserDadosGateway {

  void saveUser(UserDomain userDomain);

  List<UserDomain> findAll();

  UserDomain findById(Integer id);

  void deleteUserById(Integer id);
}
