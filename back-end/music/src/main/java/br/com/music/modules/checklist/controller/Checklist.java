package br.com.music.modules.checklist.controller;

import br.com.music.modules.checklist.controller.dto.ChecklistDto;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Valid
public interface Checklist {

  @GetMapping("/checklist/{id}")
  public ChecklistDomain findById(@PathVariable Integer id);

  @GetMapping("/checklists")
  public ResponseEntity<List<ChecklistDomain>> findAll();

  @PostMapping("/checklist")
  public ResponseEntity<String> save(@Valid @RequestBody ChecklistDto checklistDto);

  @DeleteMapping("/checklist/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id);
}
