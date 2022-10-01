package br.com.music.modules.event.usecase;

import br.com.music.modules.commum.utils.ValidateRequest;
import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
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

  public void save(EventDomain eventDomain) {

    // validateRequest.validate(eventDomain.getIdUser());
    // validateRequest.validate(eventDomain.getIdUserMaster());

    if (eventDomain.getReceive() == null) {
      eventDomain.setReceive(receiveDadosGateway.save(buildReceive(eventDomain)));
    }

    eventDadosGateway.save(eventDomain);
  }

  private ReceiveDomain buildReceive(final EventDomain eventDomain) {
    return ReceiveDomain.builder()
        .totalValueReceive(eventDomain.getValue())
        .description(eventDomain.getDescription())
        .idUser(eventDomain.getIdUser())
        .build();
  }

  public List<EventDomain> findAll() {
    return eventDadosGateway.findAll();
  }

  public EventDomain findById(Integer id) {
    return eventDadosGateway.findById(id);
  }

  public void deleteById(Integer id) {
    eventDadosGateway.deleteById(id);
  }
}
