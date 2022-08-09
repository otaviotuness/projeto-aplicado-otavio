package br.com.music.modules.event.usecase.domain.dataprovider.repository;

import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class EventRepository {

    public void save(EventDomain eventDomain) {}

    public EventDomain findById(Integer id) {
        var eventDomain = EASY_RANDOM.nextObject(EventDomain.class);

        return eventDomain;
    }

    public List<EventDomain> findAll(){
        var events = List.of(EASY_RANDOM.nextObject(EventDomain.class));

        return events;
    }
    public void deleteById(Integer id) {}
}
