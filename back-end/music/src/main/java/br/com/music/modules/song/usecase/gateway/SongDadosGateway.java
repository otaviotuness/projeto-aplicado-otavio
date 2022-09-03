package br.com.music.modules.song.usecase.gateway;

import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;

public interface SongDadosGateway {

  void save(SongDomain songDomain);

  List<SongDomain> findAll();

  SongDomain findById(Integer id);

  void deleteById(Integer id);
}
