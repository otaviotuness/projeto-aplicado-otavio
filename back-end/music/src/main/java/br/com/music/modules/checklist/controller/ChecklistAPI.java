package br.com.music.modules.checklist.controller;

import br.com.music.modules.checklist.controller.dto.ChecklistDto;
import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import br.com.music.modules.commum.anotattion.PermitAll;
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
@PermitAll
public interface ChecklistAPI {

  @GetMapping("/checklist/{id}")
  ChecklistDomain findById(@PathVariable Integer id);

  @GetMapping("/checklists")
  ResponseEntity<List<ChecklistDomain>> findAll();

  @PostMapping("/checklist")
  ResponseEntity<String> save(@Valid @RequestBody ChecklistDto checklistDto);

  @DeleteMapping("/checklist/{id}")
  ResponseEntity<String> deleteById(@PathVariable Integer id);
}
