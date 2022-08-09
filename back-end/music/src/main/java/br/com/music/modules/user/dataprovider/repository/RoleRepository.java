package br.com.music.modules.user.dataprovider.repository;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import org.springframework.stereotype.Repository;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class RoleRepository {

    public void saveRole(RoleDomain roleDomain) {}

    public RoleDomain findById(Integer id) {
        var roleDomain = EASY_RANDOM.nextObject(RoleDomain.class);

        return roleDomain;
    }
    public void deleteById(Integer id) {}
}
