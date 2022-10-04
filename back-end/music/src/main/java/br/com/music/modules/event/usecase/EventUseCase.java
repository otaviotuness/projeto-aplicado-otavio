package br.com.music.modules.event.usecase;

import br.com.music.modules.checklist.usecase.gateway.ChecklistDadosGateway;
import br.com.music.modules.commum.utils.ValidateRequest;
import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import br.com.music.modules.event.usecase.helper.EventHelper;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventUseCase {

  private final EventDadosGateway eventDadosGateway;
  private final ReceiveDadosGateway receiveDadosGateway;
  private final ChecklistDadosGateway checklistDadosGateway;
  private final SongDadosGateway songDadosGateway;
  private final ValidateRequest validateRequest;
  private final EventHelper eventHelper;

  public void save(EventDomain eventDomain) {

    validateRequest.validate(eventDomain.getIdUser(), eventDomain.getIdUserMaster());

    // caso não tenha recebimento cadastrado é um criado
    if (eventDomain.getReceive() == null) {
      eventDomain.setReceive(receiveDadosGateway.save(eventHelper.buildReceive(eventDomain)));
    }

    eventDadosGateway.save(eventDomain);
  }

  public List<EventDomain> findAll() {
    return eventDadosGateway.findAll();
  }

  public EventDomain findById(Integer id) {
    final var event = eventDadosGateway.findById(id);

    validateRequest.validate(event.getIdUser(), event.getIdUserMaster());

    return event;
  }

  public void deleteById(Integer id) {

    final var event = eventDadosGateway.findById(id);

    eventDadosGateway.deleteById(id);

    // deletando recebimentos
    Optional.ofNullable(event.getReceive())
        .ifPresent(receive -> receiveDadosGateway.deleteById(receive.getId()));

    // deletando checklists
    Optional.ofNullable(event.getChecklist()).ifPresent(checklistDadosGateway::deleteAll);
  }
}
