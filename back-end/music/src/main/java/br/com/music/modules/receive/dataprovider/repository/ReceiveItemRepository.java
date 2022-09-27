package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceiveItemRepository extends JpaRepository<ReceiveItemDomain, Integer> {

  @Transactional
  @Query(value = "DELETE FROM receive_item WHERE receive_id = ?1", nativeQuery = true)
  void deleteItems(Integer idReceive);
}
