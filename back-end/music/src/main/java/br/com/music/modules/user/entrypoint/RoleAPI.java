package br.com.music.modules.user.entrypoint;

import br.com.music.modules.commum.anotattion.PermitAll;
import br.com.music.modules.user.domain.RoleDomain;
import br.com.music.modules.user.entrypoint.dto.RoleDto;
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
