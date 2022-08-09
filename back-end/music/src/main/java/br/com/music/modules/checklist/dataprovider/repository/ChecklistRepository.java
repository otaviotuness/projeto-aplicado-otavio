package br.com.music.modules.checklist.dataprovider.repository;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class ChecklistRepository {

    public void save(ChecklistDomain checklistDomain) {}

    public ChecklistDomain findById(Integer id) {
        var checklistDomain = EASY_RANDOM.nextObject(ChecklistDomain.class);

        return checklistDomain;
    }

    public List<ChecklistDomain> findAll(){
        var checkList = Arrays.asList(EASY_RANDOM.nextObject(ChecklistDomain.class));

        return checkList;
    }

    public void deleteById(Integer id) {}
}
