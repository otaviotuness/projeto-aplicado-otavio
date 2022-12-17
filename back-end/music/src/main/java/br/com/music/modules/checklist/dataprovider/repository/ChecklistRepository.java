package br.com.music.modules.checklist.dataprovider.repository;

import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<ChecklistDomain, Integer> {

  @Query(
      value = "SELECT * FROM checklist where ((?1 = true) or (?2 = id_user) or (?3 = id_user))",
      nativeQuery = true)
  List<ChecklistDomain> findAllChecklists(boolean isAdm, Integer idUser, Integer idUserMaster);
}
