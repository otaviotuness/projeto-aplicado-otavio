package br.com.music.modules.checklist.usecase.gateway;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import java.util.List;

public interface ChecklistDadosGateway {

  void save(ChecklistDomain checklistDomain);

  List<ChecklistDomain> findAll();

  ChecklistDomain findById(Integer id);

  void deleteById(Integer id);

  void deleteAll(List<ChecklistDomain> checklistDomains);
}
