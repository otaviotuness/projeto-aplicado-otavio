package br.com.music.modules.event.controller;

import br.com.music.modules.event.controller.dto.EventDto;
import br.com.music.modules.event.controller.dto.EventResponseDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Valid
public interface Event {

  @GetMapping("/events")
  public List<EventResponseDto> findAll();

  @GetMapping("/event/{id}")
  public EventResponseDto findById(@PathVariable Integer id);

  @PostMapping("/event")
  public ResponseEntity<String> save(@Valid @RequestBody EventDto eventDto);

  @DeleteMapping("/event/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id);
}
