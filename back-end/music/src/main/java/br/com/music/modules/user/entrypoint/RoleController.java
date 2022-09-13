package br.com.music.modules.user.entrypoint;

import br.com.music.modules.user.entrypoint.dto.RoleDto;
import br.com.music.modules.user.entrypoint.mapper.RoleMapper;
import br.com.music.modules.user.usecase.RoleUseCase;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleController {

  private final RoleMapper roleMapper;
  private final RoleUseCase roleUseCase;

  @GetMapping("/role")
  private List<RoleDomain> getRole() {

    List<RoleDomain> roles = roleUseCase.getRole();

    return roles;
  }

  @PostMapping("/role")
  private ResponseEntity<String> createRole(@Valid @RequestBody RoleDto roleDto) {

    var roleDomain = roleMapper.toDomain(roleDto);

    roleUseCase.saveRole(roleDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @GetMapping("/role/{id}")
  private RoleDomain createRole(@PathVariable Integer id) {

    var roleDomain = roleUseCase.getRoleById(id);

    return roleDomain.get();
  }
}
