package br.com.music.modules.checklist.dataprovider.database;

import br.com.music.modules.checklist.dataprovider.repository.ChecklistRepository;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import br.com.music.modules.checklist.usecase.gateway.ChecklistDadosGateway;
import br.com.music.modules.utils.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChecklistMySQLDataProvider implements ChecklistDadosGateway {

  private final ChecklistRepository checklistRepository;

  @Override
  public void save(ChecklistDomain checklistDomain) {
    log.info("Save checklist.");

    checklistRepository.save(checklistDomain);

    log.info("Save checklist successfully!");
  }

  @Override
  public List<ChecklistDomain> findAll() {
    log.info("Find all checklists");

    var checklistDomains = checklistRepository.findAll();

    log.info("Find all checklists successfully");

    return checklistDomains;
  }

  @Override
  public ChecklistDomain findById(Integer id) {
    log.info("Find checklists by id: [{}}.", id);

    var checklistDomain =
        checklistRepository.findById(id).orElseThrow(() -> NotFoundException.with(id));

    log.info("Find successfully checklists by id: [{}}.", id);

    return checklistDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete checklist by id: [{}}.", id);

    checklistRepository.deleteById(id);

    log.info("Delete successfully checklist by id: [{}}.", id);
  }
}
