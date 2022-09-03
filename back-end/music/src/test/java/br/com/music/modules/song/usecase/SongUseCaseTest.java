package br.com.music.modules.song.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.music.modules.song.usecase.domain.SongDomain;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SongUseCaseTest {

  private static final Integer SONG_ID = 123;

  @InjectMocks SongUseCase songUseCase;

  @Mock private SongDadosGateway songDadosGateway;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(songDadosGateway);
  }

  @Test
  void givenSave_thenReturnSuccessfully() {
    doNothing().when(songDadosGateway).save(any());

    songUseCase.save(any());

    verify(songDadosGateway, times(1)).save(any());
  }

  @Test
  void givenFindAll_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);

    when(songUseCase.findAll()).thenReturn(List.of(song));

    songUseCase.findAll();

    verify(songDadosGateway, times(1)).findAll();
  }

  @Test
  void givenFindById_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);

    when(songUseCase.findById(SONG_ID)).thenReturn(song);

    songUseCase.findById(SONG_ID);

    verify(songDadosGateway, times(1)).findById(SONG_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    doNothing().when(songDadosGateway).deleteById(SONG_ID);

    songUseCase.deleteById(SONG_ID);

    verify(songDadosGateway, times(1)).deleteById(SONG_ID);
  }
}