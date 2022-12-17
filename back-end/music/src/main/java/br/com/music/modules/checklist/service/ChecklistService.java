package br.com.music.modules.checklist.service;

import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import br.com.music.modules.checklist.service.gateway.ChecklistDadosGateway;
import br.com.music.modules.commum.utils.ValidateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChecklistService {

  private final ChecklistDadosGateway checklistDadosGateway;
  private final ValidateRequest validateRequest;

  public void save(ChecklistDomain checklistDomain) {

    validateRequest.validate(checklistDomain.getIdUser());

    checklistDadosGateway.save(checklistDomain);
  }

  public List<ChecklistDomain> findAll() {
    return checklistDadosGateway.findAll();
  }

  public ChecklistDomain findById(Integer id) {
    final var checklist = checklistDadosGateway.findById(id);

    validateRequest.validate(checklist.getIdUser());

    return checklist;
  }

  public void deleteById(Integer id) {

    final var checklist = checklistDadosGateway.findById(id);

    validateRequest.validate(checklist.getIdUser());

    checklistDadosGateway.deleteById(id);
  }
}
