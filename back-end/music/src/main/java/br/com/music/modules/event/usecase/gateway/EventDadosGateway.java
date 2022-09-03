package br.com.music.modules.event.usecase.gateway;

import br.com.music.modules.event.usecase.domain.EventDomain;
import java.util.List;

public interface EventDadosGateway {

  void save(EventDomain eventDomain);

  List<EventDomain> findAll();

  EventDomain findById(Integer id);

  void deleteById(Integer id);
}
