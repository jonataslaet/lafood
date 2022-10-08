package com.github.jonataslaet.lafood.cadastro.resources.dtos;

import com.github.jonataslaet.lafood.cadastro.entities.Localizacao;
import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface LocalizacaoMapper {

    Localizacao toEntity(LocalizacaoDto localizacaoDto);
}
