package br.com.music.modules.song.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.configTest.TestWithMySQL;
import br.com.music.modules.song.dataprovider.repository.SongRepository;
import br.com.music.modules.song.usecase.domain.SongDomain;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;

@EnableAutoConfiguration
class SongUseCaseIT extends TestWithMySQL {

  @Autowired private SongUseCase songUseCase;

  @Autowired private SongRepository songRepository;

  @Spy private SongDadosGateway songDadosGateway;

  @BeforeEach
  void beforeEach(){
    songRepository.deleteAll();
  }

  @Test
  void shouldSave_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);

    Assertions.assertEquals(0, songRepository.count());

    songUseCase.save(song);

    final var expectedSong = songRepository.getOne(song.getId());

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(song.getIdUser(), expectedSong.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldSaveAndUpdate_thenReturnSuccessfully() {
    final var expectedLink = "Alter link";
    final var expectedDescription = "Alter description";
    final var expectedIdUser = 1;

    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    songUseCase.save(song);

    final var newSong = song;
    newSong.setDescription(expectedDescription);
    newSong.setLink(expectedLink);
    newSong.setIdUser(expectedIdUser);

    songUseCase.save(newSong);

    final var expectedSong = songRepository.getOne(newSong.getId());

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(expectedSong.getIdUser(), expectedIdUser);
    Assertions.assertEquals(expectedSong.getLink(), expectedLink);
    Assertions.assertEquals(expectedSong.getDescription(), expectedDescription);
  }

  @Test
  void shouldFindById_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    songUseCase.save(song);

    Assertions.assertEquals(1, songRepository.count());

    final var expectedSong = songUseCase.findById(song.getId());

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(song.getIdUser(), expectedSong.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldFindAll_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    songUseCase.save(song);

    Assertions.assertEquals(1, songRepository.count());

    final var expectedSong = songUseCase.findAll().get(0);

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(song.getIdUser(), expectedSong.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldDelete_thenReturnSuccessfully() {
    final var expectedCountSongs = 0;
    final var song = EASY_RANDOM.nextObject(SongDomain.class);

    Assertions.assertEquals(0, songRepository.count());

    songUseCase.save(song);

    songUseCase.deleteById(song.getId());

    Assertions.assertEquals(0, songRepository.count());

    final var expectedSongCount = songRepository.getOne(song.getId());

    Assertions.assertEquals(expectedSongCount, expectedCountSongs);
  }
}
