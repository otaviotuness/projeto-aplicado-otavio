package br.com.music.modules.song.controller;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.song.controller.dto.SongDto;
import br.com.music.modules.song.controller.mapper.SongMapper;
import br.com.music.modules.song.usecase.SongUseCase;
import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;
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
public class SongController implements Song {

  private final SongMapper songMapper;
  private final SongUseCase songUseCase;
  private final UserInfo userInfo;

  public SongDomain findById(final Integer id) {

    return songUseCase.findById(id);
  }

  public ResponseEntity<List<SongDomain>> findAll() {

    var songs = songUseCase.findAll();

    return ResponseEntity.ok(songs);
  }

  public ResponseEntity<String> save(@RequestBody SongDto songDto) {

    var song = songMapper.toDomain(songDto);

    if (song.getIdUser() == null) {
      song.setIdUser(userInfo.getUserId());
    }

    songUseCase.save(song);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    songUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
