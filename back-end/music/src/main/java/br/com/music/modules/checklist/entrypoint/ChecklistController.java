package br.com.music.modules.checklist.entrypoint;

import br.com.music.modules.checklist.entrypoint.dto.ChecklistDto;
import br.com.music.modules.checklist.entrypoint.mapper.ChecklistMapper;
import br.com.music.modules.checklist.usecase.ChecklistUseCase;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChecklistController {

  private final ChecklistMapper checklistMapper;
  private final ChecklistUseCase checklistUseCase;

  @GetMapping("/checklist/{id}")
  public ChecklistDomain findById(@PathVariable Integer id) {

    var checklistDomain = checklistUseCase.findById(id);

    return checklistDomain;
  }

  @GetMapping("/checklists")
  public ResponseEntity<List<ChecklistDomain>> findAll() {

    var checklist = checklistUseCase.findAll();

    return ResponseEntity.ok(checklist);
  }

  @PostMapping("/checklist")
  public ResponseEntity<String> save(@Valid @RequestBody ChecklistDto checklistDto) {

    var checklist = checklistMapper.toDomain(checklistDto);

    checklistUseCase.save(checklist);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/checklist/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    checklistUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
