package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.ReceiveItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveItemRepository extends JpaRepository<ReceiveItem, Integer> {}
