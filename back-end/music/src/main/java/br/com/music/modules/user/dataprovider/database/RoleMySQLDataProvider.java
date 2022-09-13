package br.com.music.modules.user.dataprovider.database;

import br.com.music.modules.user.dataprovider.repository.RoleRepository;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.gateway.RoleDadosGateway;
import java.util.List;
import java.util.Optional;
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

    roleRepository.save(roleDomain);

    log.info("Save role sucessfully!");
  }

  @Override
  public List<RoleDomain> getRole() {
    log.info("Search roles.");

    List<RoleDomain> roles = roleRepository.findAll();

    log.info("Search roles sucessfully!");

    return roles;
  }

  @Override
  public Optional<RoleDomain> getRoleById(Integer id) {
    log.info("Search role.");

    Optional<RoleDomain> roles = roleRepository.findById(id);

    log.info("Search role sucessfully!");

    return roles;
  }
}
