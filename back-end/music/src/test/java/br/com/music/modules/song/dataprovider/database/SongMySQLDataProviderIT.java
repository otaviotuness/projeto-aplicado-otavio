package br.com.music.modules.song.dataprovider.database;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.configTest.TestWithMySQL;
import br.com.music.modules.song.dataprovider.repository.SongRepository;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
class SongMySQLDataProviderIT extends TestWithMySQL {

  @Autowired private SongMySQLDataProvider songMySQLDataProvider;

  @Autowired private SongRepository songRepository;

  @Autowired private UserInfo userInfo;

  @BeforeEach
  void beforeEach() {
    songRepository.deleteAll();
  }

  @Test
  void shouldSave_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    song.setId(null);

    Assertions.assertEquals(0, songRepository.count());

    songMySQLDataProvider.save(song);

    final var expectedSong = songRepository.findAll().get(0);

    Assertions.assertNotNull(expectedSong);
    Assertions.assertNotNull(song.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldSaveAndUpdate_thenReturnSuccessfully() {
    final var expectedLink = "Alter link";
    final var expectedDescription = "Alter description";
    final var expectedIdUser = 1;

    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    songMySQLDataProvider.save(song);

    final var songUpdate = songRepository.findAll().get(0);
    songUpdate.setDescription(expectedDescription);
    songUpdate.setLink(expectedLink);
    songUpdate.setIdUser(expectedIdUser);

    songMySQLDataProvider.save(songUpdate);

    final var expectedSong = songRepository.findById(songUpdate.getId()).get();

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(expectedSong.getIdUser(), expectedIdUser);
    Assertions.assertEquals(expectedSong.getLink(), expectedLink);
    Assertions.assertEquals(expectedSong.getDescription(), expectedDescription);
  }

  @Test
  void shouldFindById_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    song.setId(null);
    songMySQLDataProvider.save(song);

    final var expectedSong = songMySQLDataProvider.findById(song.getId());

    Assertions.assertNotNull(expectedSong);
    Assertions.assertNotNull(song.getId());
    Assertions.assertEquals(song.getIdUser(), expectedSong.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldFindAll_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    song.setIdUser(0);
    songMySQLDataProvider.save(song);

    Assertions.assertEquals(1, songRepository.count());

    final var expectedSong = songMySQLDataProvider.findAll().get(0);

    Assertions.assertNotNull(expectedSong);
    Assertions.assertEquals(song.getIdUser(), expectedSong.getIdUser());
    Assertions.assertEquals(song.getLink(), expectedSong.getLink());
    Assertions.assertEquals(song.getDescription(), expectedSong.getDescription());
  }

  @Test
  void shouldDelete_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    song.setId(null);

    final var songSave = songRepository.save(song);

    songMySQLDataProvider.deleteById(songSave.getId());

    Assertions.assertEquals(0, songRepository.count());
  }
}
