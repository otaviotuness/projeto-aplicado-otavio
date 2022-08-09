package br.com.music.modules.song.entrypoint;

import br.com.music.modules.song.entrypoint.dto.SongDto;
import br.com.music.modules.song.entrypoint.mapper.SongMapper;
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
public class SongController {

    private final SongMapper songMapper;
    private final SongUseCase songUseCase;

    @GetMapping("/song/{id}")
    public SongDomain findById(@PathVariable Integer id) {

        var songDomain = songUseCase.findById(id);

        return songDomain;
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDomain>> findAll() {

        var songs = songUseCase.findAll();

        return ResponseEntity.ok(songs);
    }

    @PostMapping("/song")
    public ResponseEntity<String> save(@Valid @RequestBody SongDto songDto) {

        var song = songMapper.toDomain(songDto);

        songUseCase.save(song);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {

        songUseCase.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



}
