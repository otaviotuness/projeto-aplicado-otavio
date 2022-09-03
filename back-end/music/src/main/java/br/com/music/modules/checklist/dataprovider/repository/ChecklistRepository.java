package br.com.music.modules.checklist.dataprovider.repository;

import static br.com.music.modules.utils.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ChecklistRepository {

  public void save(ChecklistDomain checklistDomain) {}

  public ChecklistDomain findById(Integer id) {
    var checklistDomain = EASY_RANDOM.nextObject(ChecklistDomain.class);

    return checklistDomain;
  }

  public List<ChecklistDomain> findAll() {
    var checkList = Arrays.asList(EASY_RANDOM.nextObject(ChecklistDomain.class));

    return checkList;
  }

  public void deleteById(Integer id) {}
}
