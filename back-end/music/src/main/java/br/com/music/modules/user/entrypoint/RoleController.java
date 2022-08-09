package br.com.music.modules.user.entrypoint;

import br.com.music.modules.user.entrypoint.dto.RoleDto;
import br.com.music.modules.user.entrypoint.mapper.RoleMapper;
import br.com.music.modules.user.usecase.RoleUseCase;
import br.com.music.modules.user.usecase.domain.RoleDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleMapper roleMapper;
    private final RoleUseCase roleUseCase;


    @GetMapping("/role/{id}")
    public RoleDomain findById(@PathVariable Integer id) {

        var roleDomain = roleUseCase.findById(id);

        return roleDomain;
    }

    @PostMapping("/role")
    public ResponseEntity<String> saveRole(@Valid @RequestBody RoleDto roleDto) {

        var roleDomain = roleMapper.toDomain(roleDto);

        roleUseCase.saveRole(roleDomain);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) {

        roleUseCase.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



}
