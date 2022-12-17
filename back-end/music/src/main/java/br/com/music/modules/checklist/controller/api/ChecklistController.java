package br.com.music.modules.checklist.controller.api;

import static br.com.music.modules.commum.anotattion.TypePermissions.ADMIN_MUSICIAN;

import br.com.music.modules.checklist.controller.ChecklistAPI;
import br.com.music.modules.checklist.controller.dto.ChecklistDto;
import br.com.music.modules.checklist.controller.mapper.ChecklistMapper;
import br.com.music.modules.checklist.service.ChecklistService;
import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import br.com.music.modules.commum.anotattion.Permission;
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
public class ChecklistController implements ChecklistAPI {

  private final ChecklistMapper checklistMapper;
  private final ChecklistService checklistService;
  private final UserInfo userInfo;

  @Permission(permissions = ADMIN_MUSICIAN)
  public ChecklistDomain findById(@PathVariable Integer id) {
    return checklistService.findById(id);
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<List<ChecklistDomain>> findAll() {

    var checklist = checklistService.findAll();

    return ResponseEntity.ok(checklist);
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<String> save(@RequestBody ChecklistDto checklistDto) {

    var checklist = checklistMapper.toDomain(checklistDto);

    if (checklist.getIdUser() == null) {
      checklist.setIdUser(userInfo.getUserId());
    }

    checklistService.save(checklist);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Permission(permissions = ADMIN_MUSICIAN)
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    checklistService.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
