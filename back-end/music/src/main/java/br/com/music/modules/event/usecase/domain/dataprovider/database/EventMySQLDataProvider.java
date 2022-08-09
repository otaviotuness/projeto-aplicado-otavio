package br.com.music.modules.event.usecase.domain.dataprovider.database;

import br.com.music.modules.event.usecase.domain.dataprovider.repository.EventRepository;
import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventMySQLDataProvider implements EventDadosGateway {

    private final EventRepository eventRepository;
    
    @Override
    public void save(EventDomain eventDomain) {
        log.info("Save event.");

        eventRepository.save(eventDomain);

        log.info("Save event successfully!");
    }

    @Override
    public List<EventDomain> findAll() {
        log.info("Find all events");

        var eventDomain = eventRepository.findAll();

        log.info("Find all events successfully");

        return eventDomain;
    }

    @Override
    public EventDomain findById(Integer id) {
        log.info("Find event by id: [{}}.", id);

        var eventDomain = eventRepository.findById(id);

        log.info("Find successfully event by id: [{}}.", id);

        return eventDomain;
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Delete event by id: [{}}.", id);

        eventRepository.deleteById(id);

        log.info("Delete successfully event by id: [{}}.", id);
    }


}
