package br.com.music.modules.event.controller;

import br.com.music.modules.event.controller.dto.EventDto;
import br.com.music.modules.event.controller.dto.EventResponseDto;
import br.com.music.modules.event.controller.mapper.EventMapper;
import br.com.music.modules.event.usecase.EventUseCase;
import br.com.music.modules.event.usecase.domain.EventDomain;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController implements Event {

  private final EventMapper eventMapper;
  private final EventUseCase eventUseCase;

  public List<EventResponseDto> findAll() {

    var eventDomain = eventUseCase.findAll();

    return eventMapper.toResponse(eventDomain);
  }

  public EventDomain findById(@PathVariable Integer id) {

    return eventUseCase.findById(id);
  }

  public ResponseEntity<String> save(@Valid @RequestBody EventDto eventDto) {

    var eventDomain = eventMapper.toDomain(eventDto);

    eventUseCase.save(eventDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    eventUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
