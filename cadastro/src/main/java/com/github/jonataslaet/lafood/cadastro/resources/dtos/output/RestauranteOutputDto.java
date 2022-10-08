package com.github.jonataslaet.lafood.cadastro.resources.dtos.output;

import com.github.jonataslaet.lafood.cadastro.resources.dtos.LocalizacaoDto;
import java.util.Date;

public class RestauranteOutputDto {

    public String nome;

    public String cnpj;

    public String proprietario;

    public LocalizacaoDto localizacao;

    public Date dataCriacao;

    public Date dataAtualizacao;
}
