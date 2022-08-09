package br.com.music.modules.song.dataprovider.repository;

import br.com.music.modules.song.usecase.domain.SongDomain;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class SongRepository {

    public void save(SongDomain songDomain) {}

    public SongDomain findById(Integer id) {
        var songDomain = EASY_RANDOM.nextObject(SongDomain.class);

        return songDomain;
    }

    public List<SongDomain> findAll(){
        var songs = Arrays.asList(EASY_RANDOM.nextObject(SongDomain.class));

        return songs;
    }
    public void deleteById(Integer id) {}
}
