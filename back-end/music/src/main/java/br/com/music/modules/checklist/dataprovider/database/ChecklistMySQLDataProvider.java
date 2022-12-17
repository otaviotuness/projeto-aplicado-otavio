package br.com.music.modules.checklist.dataprovider.database;

import br.com.music.modules.checklist.dataprovider.repository.ChecklistRepository;
import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import br.com.music.modules.checklist.service.gateway.ChecklistDadosGateway;
import br.com.music.modules.commum.exceptions.NotFoundException;
import br.com.music.modules.commum.utils.UserInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChecklistMySQLDataProvider implements ChecklistDadosGateway {

  private final ChecklistRepository checklistRepository;
  private final UserInfo userInfo;

  @Override
  public void save(ChecklistDomain checklistDomain) {
    log.info("Save checklist.");

    checklistRepository.save(checklistDomain);

    log.info("Save checklist successfully!");
  }

  @Override
  public List<ChecklistDomain> findAll() {
    log.info("Find all checklists");

    var checklists =
        checklistRepository.findAllChecklists(
            userInfo.isAdmin(), userInfo.getUserId(), userInfo.getUserIdMaster());

    log.info("Find all checklists successfully");

    return checklists;
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

  @Override
  public void deleteAll(List<ChecklistDomain> checklistDomains) {
    log.info("Delete checklist");

    checklistRepository.deleteAll(checklistDomains);

    log.info("Delete checklists successfully");
  }
}
