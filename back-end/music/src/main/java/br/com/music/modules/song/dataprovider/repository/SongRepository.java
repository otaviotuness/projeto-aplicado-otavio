package br.com.music.modules.song.dataprovider.repository;

import br.com.music.modules.song.usecase.domain.SongDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongDomain, Integer> {

  @Query(
      value = "SELECT * FROM song where ((?1 = true) or (?2 = id_user) or (?3 = id_user))",
      nativeQuery = true)
  List<SongDomain> findAllSongs(boolean isAdm, Integer idUser, Integer idUserMaster);
}
