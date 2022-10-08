package com.github.jonataslaet.lafood.cadastro.resources.dtos;

import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.input.RestauranteInputDto;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.output.RestauranteOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    Restaurante toRestauranteEntity(RestauranteInputDto restauranteInputDto);

    @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteOutputDto toRestauranteOutputDto(Restaurante restaurante);
}
