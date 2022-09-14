package br.com.music.modules.song.controller;

import br.com.music.modules.song.controller.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface Song {

  @GetMapping("/song/{id}")
  SongDomain findById(@PathVariable Integer id);

  @GetMapping("/songs")
  ResponseEntity<List<SongDomain>> findAll();

  @PostMapping("/song")
  ResponseEntity<String> save(@Valid @RequestBody SongDto songDto);

  @DeleteMapping("/song/{id}")
  ResponseEntity<String> deleteById(@PathVariable Integer id);
}
