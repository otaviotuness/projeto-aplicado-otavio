package br.com.music.modules.category.entrypoint.mapper;

import br.com.music.modules.category.entrypoint.dto.CategoryDto;
import br.com.music.modules.category.usecase.domain.CategoryDomain;
import br.com.music.modules.song.entrypoint.dto.SongDto;
import br.com.music.modules.song.usecase.domain.SongDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDomain toDomain(CategoryDto songDto);

}
