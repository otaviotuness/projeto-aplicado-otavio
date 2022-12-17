package br.com.music.modules.user.controller.api;

import static br.com.music.modules.commum.anotattion.TypePermissions.ADMIN;

import br.com.music.modules.commum.anotattion.Permission;
import br.com.music.modules.user.controller.RoleAPI;
import br.com.music.modules.user.controller.dto.RoleDto;
import br.com.music.modules.user.controller.mapper.RoleMapper;
import br.com.music.modules.user.domain.RoleDomain;
import br.com.music.modules.user.usecase.RoleUseCase;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleController implements RoleAPI {

  private final RoleMapper roleMapper;
  private final RoleUseCase roleUseCase;

  @Permission(permissions = ADMIN)
  public List<RoleDomain> getRole() {

    List<RoleDomain> roles = roleUseCase.getRole();

    return roles;
  }

  @Permission(permissions = ADMIN)
  public ResponseEntity<String> createRole(@Valid @RequestBody RoleDto roleDto) {

    var roleDomain = roleMapper.toDomain(roleDto);

    roleUseCase.saveRole(roleDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Permission(permissions = ADMIN)
  public RoleDomain createRole(@PathVariable Integer id) {

    var roleDomain = roleUseCase.getRoleById(id);

    return roleDomain.get();
  }
}
