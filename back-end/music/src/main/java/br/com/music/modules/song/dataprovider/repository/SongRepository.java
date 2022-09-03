package br.com.music.modules.song.dataprovider.repository;

import br.com.music.modules.song.usecase.domain.SongDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongDomain, Integer> {}
