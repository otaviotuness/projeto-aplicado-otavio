package br.com.music.modules.user.usecase.gateway;

import br.com.music.modules.user.usecase.domain.RoleDomain;

public interface RoleDadosGateway {

    public void saveRole(RoleDomain roleDomain);

    public RoleDomain findById(Integer id);

    public void deleteById(Integer id);
}
