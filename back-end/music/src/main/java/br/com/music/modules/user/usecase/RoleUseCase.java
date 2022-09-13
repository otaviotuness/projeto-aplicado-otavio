package br.com.music.modules.user.usecase;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.gateway.RoleDadosGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleUseCase {

  private final RoleDadosGateway roleDadosGateway;

  public void saveRole(RoleDomain roleDomain) {
    roleDadosGateway.saveRole(roleDomain);
  }

  public List<RoleDomain> getRole() {
    return roleDadosGateway.getRole();
  }

  public Optional<RoleDomain> getRoleById(Integer id) {
    return roleDadosGateway.getRoleById(id);
  }
}
