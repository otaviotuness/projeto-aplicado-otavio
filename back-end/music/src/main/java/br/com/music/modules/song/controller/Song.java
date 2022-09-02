package br.com.music.modules.song.controller;

import br.com.music.modules.song.controller.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping
@Valid
public interface Song {

  @GetMapping("/song/{id}")
  SongDomain findById(@PathVariable Integer id);

  @GetMapping("/songs")
  ResponseEntity<List<SongDomain>> findAll();

  @PostMapping("/song")
  public ResponseEntity<String> save(@Valid @RequestBody SongDto songDto);

  @DeleteMapping("/song/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id);
}
