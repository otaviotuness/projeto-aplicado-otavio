package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveItemRepository extends JpaRepository<ReceiveItemDomain, Integer> {}
