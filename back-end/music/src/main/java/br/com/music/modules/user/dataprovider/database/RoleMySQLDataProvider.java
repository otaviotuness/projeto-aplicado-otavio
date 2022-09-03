package br.com.music.modules.user.dataprovider.database;

import br.com.music.modules.user.dataprovider.repository.RoleRepository;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.gateway.RoleDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RoleMySQLDataProvider implements RoleDadosGateway {

  private final RoleRepository roleRepository;

  @Override
  public void saveRole(RoleDomain roleDomain) {
    log.info("Save role.");

    roleRepository.saveRole(roleDomain);

    log.info("Save role successfully!");
  }

  @Override
  public RoleDomain findById(Integer id) {
    log.info("Find role by id: [{}}.", id);

    var roleDomain = roleRepository.findById(id);

    log.info("Find successfully role by id: [{}}.", id);

    return roleDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete role by id: [{}}.", id);

    roleRepository.deleteById(id);

    log.info("Delete successfully role by id: [{}}.", id);
  }
}
