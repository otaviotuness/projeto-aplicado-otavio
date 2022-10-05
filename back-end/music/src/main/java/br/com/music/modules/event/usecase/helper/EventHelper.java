package br.com.music.modules.event.usecase.helper;

import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import org.springframework.stereotype.Component;

@Component
public class EventHelper {

  public ReceiveDomain buildReceive(final EventDomain eventDomain) {
    return ReceiveDomain.builder()
        .totalValueReceive(eventDomain.getValue())
        .description(eventDomain.getDescription())
        .idUser(eventDomain.getIdUserMaster())
        .build();
  }
}
