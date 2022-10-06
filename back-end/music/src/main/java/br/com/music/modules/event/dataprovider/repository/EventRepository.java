package br.com.music.modules.event.dataprovider.repository;

import br.com.music.modules.event.usecase.domain.EventDomain;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends JpaRepository<EventDomain, Integer> {

  @Query(value = "SELECT e.* FROM event e where receive_id = ?1", nativeQuery = true)
  Optional<EventDomain> findByReceiveId(final Integer receiveId);

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM checklist where event_id = ?1", nativeQuery = true)
  void deleteChecklistInEvent(final Integer eventId);

  @Query(
      value = "SELECT * FROM event where ((?1 = true) or (?2 = id_user) or (?3 = id_user))",
      nativeQuery = true)
  List<EventDomain> findAllEvents(boolean isAdm, Integer idUser, Integer idUserMaster);
}
