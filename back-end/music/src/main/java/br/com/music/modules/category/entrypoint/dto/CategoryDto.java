package br.com.music.modules.category.entrypoint.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    @NotNull
    private Integer id;

    @NotBlank
    private String description;

    @NotNull
    @Min(0)
    private Integer limit;
}
