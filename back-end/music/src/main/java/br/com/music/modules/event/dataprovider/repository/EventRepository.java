package br.com.music.modules.event.dataprovider.repository;

import br.com.music.modules.event.usecase.domain.EventDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventDomain, Integer> {}
