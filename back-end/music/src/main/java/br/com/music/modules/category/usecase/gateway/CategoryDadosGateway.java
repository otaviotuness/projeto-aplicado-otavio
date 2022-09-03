package br.com.music.modules.category.usecase.gateway;

import br.com.music.modules.category.usecase.domain.CategoryDomain;
import java.util.List;

public interface CategoryDadosGateway {

  void save(CategoryDomain categoryDomain);

  List<CategoryDomain> findAll();

  CategoryDomain findById(Integer id);

  void deleteById(Integer id);
}
