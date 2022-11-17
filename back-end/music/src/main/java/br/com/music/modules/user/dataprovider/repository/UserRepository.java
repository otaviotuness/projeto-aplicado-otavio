package br.com.music.modules.user.dataprovider.repository;

import br.com.music.modules.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Integer> {

  UserDomain findByEmail(String email);
}
