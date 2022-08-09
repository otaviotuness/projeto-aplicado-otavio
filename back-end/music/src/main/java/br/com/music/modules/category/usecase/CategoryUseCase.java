package br.com.music.modules.category.usecase;

import br.com.music.modules.category.usecase.domain.CategoryDomain;
import br.com.music.modules.category.usecase.gateway.CategoryDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryDadosGateway categoryDadosGateway;

    public void save(CategoryDomain categoryDomain) {
        categoryDadosGateway.save(categoryDomain);
    }

    public List<CategoryDomain> findAll(){
        return categoryDadosGateway.findAll();
    }

    public CategoryDomain findById(Integer id){
        return categoryDadosGateway.findById(id);
    }

    public void deleteById(Integer id){
        categoryDadosGateway.deleteById(id);
    }

}
