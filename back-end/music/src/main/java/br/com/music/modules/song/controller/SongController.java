package br.com.music.modules.song.controller;

import br.com.music.modules.song.controller.dto.SongDto;
import br.com.music.modules.song.controller.mapper.SongMapper;
import br.com.music.modules.song.usecase.SongUseCase;
import br.com.music.modules.song.usecase.domain.SongDomain;
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

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController implements Song{

    private final SongMapper songMapper;
    private final SongUseCase songUseCase;

    public SongDomain findById(@PathVariable Integer id) {

        var songDomain = songUseCase.findById(id);

        return songDomain;
    }

    public ResponseEntity<List<SongDomain>> findAll() {

        var songs = songUseCase.findAll();

        return ResponseEntity.ok(songs);
    }

    public ResponseEntity<String> save(@RequestBody SongDto songDto) {

        var song = songMapper.toDomain(songDto);

        songUseCase.save(song);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public ResponseEntity<String> deleteById(@PathVariable Integer id) {

        songUseCase.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
