package br.com.music.modules.category.entrypoint;

import br.com.music.modules.category.entrypoint.dto.CategoryDto;
import br.com.music.modules.category.entrypoint.mapper.CategoryMapper;
import br.com.music.modules.category.usecase.CategoryUseCase;
import br.com.music.modules.category.usecase.domain.CategoryDomain;
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
public class CategoryController {

  private final CategoryMapper categoryMapper;
  private final CategoryUseCase categoryUseCase;

  @GetMapping("/categories")
  public List<CategoryDomain> findAll() {

    var categoryDomain = categoryUseCase.findAll();

    return categoryDomain;
  }

  @GetMapping("/category/{id}")
  public CategoryDomain findById(@PathVariable Integer id) {

    var categoryDomain = categoryUseCase.findById(id);

    return categoryDomain;
  }

  @PostMapping("/category")
  public ResponseEntity<String> saveRole(@Valid @RequestBody CategoryDto categoryDto) {

    var categoryDomain = categoryMapper.toDomain(categoryDto);

    categoryUseCase.save(categoryDomain);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<String> deleteRole(@PathVariable Integer id) {

    categoryUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
