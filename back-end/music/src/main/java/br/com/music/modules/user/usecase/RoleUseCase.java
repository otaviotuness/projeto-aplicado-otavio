package br.com.music.modules.user.usecase;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import br.com.music.modules.user.usecase.gateway.RoleDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleUseCase {

    private final RoleDadosGateway roleDadosGateway;

    public void saveRole(RoleDomain roleDomain) {
        roleDadosGateway.saveRole(roleDomain);
    }

    public RoleDomain findById(Integer id){
        return roleDadosGateway.findById(id);
    }

    public void deleteById(Integer id){
        roleDadosGateway.deleteById(id);
    }


}
