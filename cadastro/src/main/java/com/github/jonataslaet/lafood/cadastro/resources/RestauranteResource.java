package com.github.jonataslaet.lafood.cadastro.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.LocalizacaoMapper;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.RestauranteMapper;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.input.RestauranteInputDto;
import com.github.jonataslaet.lafood.cadastro.resources.dtos.output.RestauranteOutputDto;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @Inject
    LocalizacaoMapper localizacaoMapper;

    @GET
    public List<RestauranteOutputDto> buscarRestaurantes() {
        Stream<Restaurante> restauranteStream = Restaurante.streamAll();
        return restauranteStream.map(restaurante -> restauranteMapper.toRestauranteOutputDto(restaurante)).collect(Collectors.toList());
    }
    
    @POST
    @Transactional
    public Response adicionar(RestauranteInputDto restauranteInputDto) {
        restauranteMapper.toRestauranteEntity(restauranteInputDto).persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, RestauranteInputDto restauranteDto) {
    	Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);
    	if (restauranteOptional.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOptional.get();
    	restaurante.proprietario = restauranteDto.proprietario;
        restaurante.nome = restauranteDto.nome;
        restaurante.localizacao = localizacaoMapper.toEntity(restauranteDto.localizacao);
    	restaurante.persist();
    	return Response.status(Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);
    	if (restauranteOptional.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOptional.get();
    	restaurante.delete();
    	return Response.status(Status.NO_CONTENT).build();
    }
}