package br.com.music.modules.user.usecase.gateway;

import br.com.music.modules.user.domain.RoleDomain;
import java.util.List;
import java.util.Optional;

public interface RoleDadosGateway {

  void saveRole(RoleDomain roleDomain);

  List<RoleDomain> getRole();

  Optional<RoleDomain> getRoleById(Integer id);
}
