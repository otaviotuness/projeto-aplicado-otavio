package br.com.music.modules.song.usecase;

import br.com.music.modules.commum.utils.ValidateRequest;
import br.com.music.modules.song.usecase.domain.SongDomain;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongUseCase {

  private final SongDadosGateway songDadosGateway;
  private final ValidateRequest validateRequest;

  public void save(SongDomain userDomain) {
    songDadosGateway.save(userDomain);
  }

  public List<SongDomain> findAll() {
    return songDadosGateway.findAll();
  }

  public SongDomain findById(final Integer idSong) {

    final var songDomain = songDadosGateway.findById(idSong);

    validateRequest.validate(songDomain.getIdUser());

    return songDomain;
  }

  public void deleteById(Integer id) {
    songDadosGateway.deleteById(id);
  }
}
