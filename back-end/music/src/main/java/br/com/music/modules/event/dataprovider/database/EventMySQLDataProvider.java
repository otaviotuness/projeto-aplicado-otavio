package br.com.music.modules.event.dataprovider.database;

import br.com.music.modules.checklist.dataprovider.repository.ChecklistRepository;
import br.com.music.modules.commum.exceptions.NotFoundException;
import br.com.music.modules.event.dataprovider.repository.EventRepository;
import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import br.com.music.modules.receive.dataprovider.repository.ReceiveRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventMySQLDataProvider implements EventDadosGateway {

  private final EventRepository eventRepository;
  private final ChecklistRepository checklistRepository;
  private final ReceiveRepository receiveRepository;

  @Override
  public void save(EventDomain eventDomain) {
    log.info("Save event.");

    final var newEvent = eventRepository.save(eventDomain);

    eventRepository.deleteChecklistInEvent(newEvent.getId());

    Optional.ofNullable(eventDomain.getChecklist())
        .ifPresent(
            checklists -> {
              checklists.forEach(checklist -> checklist.setEvent(newEvent));
              checklistRepository.saveAll(checklists);
            });

    log.info("Save event successfully!");
  }

  @Override
  public List<EventDomain> findAll() {
    log.info("Find all events");

    final var eventDomain = eventRepository.findAll();

    log.info("Find all events successfully");

    return eventDomain;
  }

  @Override
  public EventDomain findById(Integer id) {
    log.info("Find event by id: [{}}.", id);

    var eventDomain = eventRepository.findById(id).orElseThrow(() -> NotFoundException.with(id));

    log.info("Find successfully event by id: [{}}.", id);

    return eventDomain;
  }

  @Override
  public void deleteById(Integer eventId) {
    log.info("Delete event by id: [{}}.", eventId);

    final var event = eventRepository.findById(eventId);

    // delete checklist
    eventRepository.deleteChecklistInEvent(eventId);

    // delete evento
    eventRepository.deleteById(eventId);

    // delete receive
    event.ifPresent(e -> receiveRepository.deleteById(e.getReceive().getId()));

    log.info("Delete successfully event by id: [{}}.", eventId);
  }
}
