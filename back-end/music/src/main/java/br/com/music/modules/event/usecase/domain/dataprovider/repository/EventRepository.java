package br.com.music.modules.event.usecase.domain.dataprovider.repository;

import static br.com.music.modules.utils.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.event.usecase.domain.EventDomain;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {

  public void save(EventDomain eventDomain) {}

  public EventDomain findById(Integer id) {
    var eventDomain = EASY_RANDOM.nextObject(EventDomain.class);

    return eventDomain;
  }

  public List<EventDomain> findAll() {
    var events = List.of(EASY_RANDOM.nextObject(EventDomain.class));

    return events;
  }

  public void deleteById(Integer id) {}
}
