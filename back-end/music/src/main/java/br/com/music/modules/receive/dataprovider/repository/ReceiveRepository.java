package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.Receive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveRepository extends JpaRepository<Receive, Integer> {}
