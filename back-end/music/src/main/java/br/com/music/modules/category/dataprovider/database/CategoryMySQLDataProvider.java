package br.com.music.modules.category.dataprovider.database;

import br.com.music.modules.category.dataprovider.repository.CategoryRepository;
import br.com.music.modules.category.usecase.domain.CategoryDomain;
import br.com.music.modules.category.usecase.gateway.CategoryDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CategoryMySQLDataProvider implements CategoryDadosGateway {

  private final CategoryRepository categoryRepository;

  @Override
  public void save(CategoryDomain categoryDomain) {
    log.info("Save song.");

    categoryRepository.save(categoryDomain);

    log.info("Save song successfully!");
  }

  @Override
  public List<CategoryDomain> findAll() {
    log.info("Find all categorys");

    var categoryDomains = categoryRepository.findAll();

    log.info("Find all categorys successfully");

    return categoryDomains;
  }

  @Override
  public CategoryDomain findById(Integer id) {
    log.info("Find categorys by id: [{}}.", id);

    var categoryDomain = categoryRepository.findById(id);

    log.info("Find successfully categorys by id: [{}}.", id);

    return categoryDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete category by id: [{}}.", id);

    categoryRepository.deleteById(id);

    log.info("Delete successfully category by id: [{}}.", id);
  }
}
