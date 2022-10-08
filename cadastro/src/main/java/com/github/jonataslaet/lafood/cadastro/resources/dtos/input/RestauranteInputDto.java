package com.github.jonataslaet.lafood.cadastro.resources.dtos.input;

import com.github.jonataslaet.lafood.cadastro.resources.dtos.LocalizacaoDto;
import com.github.jonataslaet.lafood.cadastro.services.validations.RestauranteInputValid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestauranteInputValid
public class RestauranteInputDto {

    @Size(min = 2, max = 128)
    public String nome;

    @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}")
    @NotNull
    public String cnpj;

    public String proprietario;

    public LocalizacaoDto localizacao;
}

