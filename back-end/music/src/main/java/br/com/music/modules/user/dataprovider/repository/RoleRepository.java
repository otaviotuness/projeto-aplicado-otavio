package br.com.music.modules.user.dataprovider.repository;

import static br.com.music.modules.utils.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

  public void saveRole(RoleDomain roleDomain) {}

  public RoleDomain findById(Integer id) {
    var roleDomain = EASY_RANDOM.nextObject(RoleDomain.class);

    return roleDomain;
  }

  public void deleteById(Integer id) {}
}
