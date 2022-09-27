package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveRepository extends JpaRepository<ReceiveDomain, Integer> {

  @Query(
      value = "SELECT r.* FROM receive r where ((?1 = true) or (?2 = id_user) or (?3 = id_user))",
      nativeQuery = true)
  List<ReceiveDomain> findAllReceive(boolean isAdm, Integer idUser, Integer idUserMaster);
}
