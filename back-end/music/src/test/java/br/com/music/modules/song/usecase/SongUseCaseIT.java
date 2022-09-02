package br.com.music.modules.song.usecase;

import br.com.music.modules.configTest.IntegrationTest;
import br.com.music.modules.song.dataprovider.repository.SongRepository;
import br.com.music.modules.song.usecase.domain.SongDomain;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;

@IntegrationTest
public class SongUseCaseIT {

  @Autowired
  private SongUseCase songUseCase;

  @Autowired
  private SongRepository songRepository;

  @Spy
  private SongDadosGateway songDadosGateway;

  @Test
  void givenTest(){
    final var song = EASY_RANDOM.nextObject(SongDomain.class);

    Assertions.assertEquals(0, songRepository.count());

    songUseCase.save(song);

    final var expectedSong = songRepository.getOne(song.getId());

    Assertions.assertNotNull(expectedSong);
  }
}
