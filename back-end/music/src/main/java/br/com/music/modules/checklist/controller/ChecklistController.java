package br.com.music.modules.checklist.controller;

import br.com.music.modules.checklist.controller.dto.ChecklistDto;
import br.com.music.modules.checklist.controller.mapper.ChecklistMapper;
import br.com.music.modules.checklist.usecase.ChecklistUseCase;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import br.com.music.modules.commum.utils.UserInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChecklistController implements Checklist {

  private final ChecklistMapper checklistMapper;
  private final ChecklistUseCase checklistUseCase;
  private final UserInfo userInfo;

  public ChecklistDomain findById(@PathVariable Integer id) {
    return checklistUseCase.findById(id);
  }

  public ResponseEntity<List<ChecklistDomain>> findAll() {

    var checklist = checklistUseCase.findAll();

    return ResponseEntity.ok(checklist);
  }

  public ResponseEntity<String> save(@RequestBody ChecklistDto checklistDto) {

    var checklist = checklistMapper.toDomain(checklistDto);

    if (checklist.getIdUser() == null) {
      checklist.setIdUser(userInfo.getUserId());
    }

    checklistUseCase.save(checklist);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    checklistUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
