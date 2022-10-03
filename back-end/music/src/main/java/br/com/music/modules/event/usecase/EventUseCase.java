package br.com.music.modules.event.usecase;

import br.com.music.modules.commum.utils.ValidateRequest;
import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import br.com.music.modules.event.usecase.helper.EventHelper;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventUseCase {

  private final EventDadosGateway eventDadosGateway;
  private final ReceiveDadosGateway receiveDadosGateway;
  private final ValidateRequest validateRequest;
  private final EventHelper eventHelper;

  public void save(EventDomain eventDomain) {

    validateRequest.validate(eventDomain.getIdUser(), eventDomain.getIdUserMaster());

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
    // deletar filhos
    eventDadosGateway.deleteById(id);
  }
}
