package br.com.music.modules.event.entrypoint;

import br.com.music.modules.event.entrypoint.dto.EventDto;
import br.com.music.modules.event.entrypoint.mapper.EventMapper;
import br.com.music.modules.event.usecase.EventUseCase;
import br.com.music.modules.event.usecase.domain.EventDomain;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController {

  private final EventMapper eventMapper;
  private final EventUseCase eventUseCase;

  @GetMapping("/events")
  public List<EventDomain> findAll() {

    var eventDomains = eventUseCase.findAll();

    return eventDomains;
  }

  @GetMapping("/event/{id}")
  public EventDomain findById(@PathVariable Integer id) {

    var eventDomain = eventUseCase.findById(id);

    return eventDomain;
  }

  @PostMapping("/event")
  public ResponseEntity<String> save(@Valid @RequestBody EventDto eventDto) {

    var eventDomain = eventMapper.toDomain(eventDto);

    eventUseCase.save(eventDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/event/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    eventUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
