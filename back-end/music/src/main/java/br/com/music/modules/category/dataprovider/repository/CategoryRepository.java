package br.com.music.modules.category.dataprovider.repository;

import br.com.music.modules.category.usecase.domain.CategoryDomain;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class CategoryRepository {

    public void save(CategoryDomain categoryDomain) {}

    public CategoryDomain findById(Integer id) {
        var categoryDomain = EASY_RANDOM.nextObject(CategoryDomain.class);

        return categoryDomain;
    }

    public List<CategoryDomain> findAll(){
        var categorys = Arrays.asList(EASY_RANDOM.nextObject(CategoryDomain.class));

        return categorys;
    }

    public void deleteById(Integer id) {}
}
