package br.com.music.modules.event.usecase.domain;

import br.com.music.modules.song.usecase.domain.SongDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "event")
public class EventDomain {

    private Integer id;
    private Integer description;
    private String date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_music",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<SongDomain> songsDomain = new HashSet<>();

}
