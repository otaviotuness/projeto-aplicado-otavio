package br.com.music.modules.song.dataprovider.database;

import br.com.music.modules.song.dataprovider.repository.SongRepository;
import br.com.music.modules.song.usecase.domain.SongDomain;
import br.com.music.modules.song.usecase.gateway.SongDadosGateway;
import br.com.music.modules.utils.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SongMySQLDataProvider implements SongDadosGateway {

  private final SongRepository songRepository;

  @Override
  public void save(SongDomain songDomain) {
    log.info("Save song.");

    songRepository.save(songDomain);

    log.info("Save song successfully!");
  }

  @Override
  public List<SongDomain> findAll() {
    log.info("Find all songs");

    var songDomain = songRepository.findAll();

    log.info("Find all songs successfully");

    return songDomain;
  }

  @Override
  public SongDomain findById(Integer id) {
    log.info("Find song by id: [{}}.", id);

    var songDomain = songRepository.findById(id).orElseThrow(() -> NotFoundException.with(id));

    log.info("Find successfully song by id: [{}}.", id);

    return songDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete song by id: [{}}.", id);

    songRepository.deleteById(id);

    log.info("Delete successfully song by id: [{}}.", id);
  }
}
