package br.com.music.modules.category.entrypoint.mapper;

import br.com.music.modules.category.entrypoint.dto.CategoryDto;
import br.com.music.modules.category.usecase.domain.CategoryDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDomain toDomain(CategoryDto songDto);

}
