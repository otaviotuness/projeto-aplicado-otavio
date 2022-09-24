package br.com.music.modules.song.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.commum.utils.ValidateRequest;
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
  private static final Integer USER_ID = 123;

  @InjectMocks SongUseCase songUseCase;

  @Mock private SongDadosGateway songDadosGateway;

  @Mock private UserInfo userInfo;

  @Mock private ValidateRequest validateRequest;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(songDadosGateway);
  }

  @Test
  void givenSave_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    doNothing().when(songDadosGateway).save(song);

    songUseCase.save(song);

    verify(songDadosGateway, times(1)).save(song);
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

    when(songDadosGateway.findById(SONG_ID)).thenReturn(song);

    doNothing().when(validateRequest).validate(song.getIdUser());

    songUseCase.findById(SONG_ID);

    verify(songDadosGateway, times(1)).findById(SONG_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    final var song = EASY_RANDOM.nextObject(SongDomain.class);
    song.setId(SONG_ID);

    when(songDadosGateway.findById(SONG_ID)).thenReturn(song);

    doNothing().when(songDadosGateway).deleteById(SONG_ID);

    doNothing().when(validateRequest).validate(song.getIdUser());

    songUseCase.deleteById(SONG_ID);

    verify(songDadosGateway, times(1)).deleteById(SONG_ID);
  }
}
