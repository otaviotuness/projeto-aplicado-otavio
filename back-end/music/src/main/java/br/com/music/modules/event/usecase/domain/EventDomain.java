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
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class EventDomain {

    private Integer id;
    private Integer description;
    private String date;
    private Set<SongDomain> songsDomain = new HashSet<>();

}
