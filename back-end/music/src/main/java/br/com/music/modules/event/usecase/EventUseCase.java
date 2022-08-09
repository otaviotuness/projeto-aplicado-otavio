package br.com.music.modules.event.usecase;

import br.com.music.modules.event.usecase.domain.EventDomain;
import br.com.music.modules.event.usecase.gateway.EventDadosGateway;
import br.com.music.modules.user.usecase.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventUseCase {

    private final EventDadosGateway eventDadosGateway;

    public void save(EventDomain eventDomain) {
        eventDadosGateway.save(eventDomain);
    }

    public List<EventDomain> findAll(){
        return eventDadosGateway.findAll();
    }

    public EventDomain findById(Integer id){
        return eventDadosGateway.findById(id);
    }

    public void deleteById(Integer id){
        eventDadosGateway.deleteById(id);
    }

}
