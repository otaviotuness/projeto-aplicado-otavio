package br.com.music.modules.user.dataprovider.repository;

import br.com.music.modules.user.usecase.domain.RoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDomain, Integer> {}
