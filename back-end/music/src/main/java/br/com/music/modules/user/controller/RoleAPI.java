package br.com.music.modules.user.controller;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.user.controller.dto.RoleDto;
import br.com.music.modules.user.domain.RoleDomain;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@PermitAll
public interface RoleAPI {

  List<RoleDomain> getRole();

  ResponseEntity<String> createRole(@Valid @RequestBody RoleDto roleDto);

  RoleDomain createRole(@PathVariable Integer id);
}
