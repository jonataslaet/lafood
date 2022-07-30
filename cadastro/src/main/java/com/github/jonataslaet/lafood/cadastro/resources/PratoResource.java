package com.github.jonataslaet.lafood.cadastro.resources;

import java.util.Optional;

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

import com.github.jonataslaet.lafood.cadastro.entities.Prato;
import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;

@Path("/restaurantes/{restaurante_id}/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @GET
    public Response listarPratos(@PathParam("restaurante_id") Long idRestaurante){
    	Optional<Restaurante> findRestauranteById = Restaurante.findByIdOptional(idRestaurante);
    	if (findRestauranteById.isEmpty()) {
    		throw new NotFoundException("Restaurante não encontrado");
    	}
        var lista = Prato.list("restaurante", findRestauranteById.get());
        return Response.status(Status.OK).entity(lista).build();
    }
    
    @POST
    @Transactional
    public Response adicionar(@PathParam("restaurante_id") Long idRestaurante, Prato prato) {
    	Optional<Restaurante> findRestauranteById = Restaurante.findByIdOptional(idRestaurante);
    	if (findRestauranteById.isEmpty()) {
    		throw new NotFoundException("Restaurante não encontrado");
    	}
    	prato.restaurante = findRestauranteById.get();    	
    	prato.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("restaurante_id") Long idRestaurante, @PathParam("id") Long idPrato, Prato pratoNovo) {
    	
    	Optional<Restaurante> findRestauranteById = Restaurante.findByIdOptional(idRestaurante);
    	if (findRestauranteById.isEmpty()) {
    		throw new NotFoundException("Restaurante não encontrado");
    	}
    	
    	Optional<Prato> findPratoById = Prato.findByIdOptional(idPrato);
    	if (findPratoById.isEmpty()) {
    		throw new NotFoundException("Prato não encontrado");
    	}
    	Prato prato = findPratoById.get();
    	prato.nome = pratoNovo.nome;
    	prato.descricao = pratoNovo.descricao;
    	prato.preco = pratoNovo.preco;
    	prato.restaurante = pratoNovo.restaurante;
    	
    	return Response.status(Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletar(@PathParam("restaurante_id") Long idRestaurante, @PathParam("id") Long idPrato) {
    	
    	Optional<Restaurante> findRestauranteById = Restaurante.findByIdOptional(idRestaurante);
    	if (findRestauranteById.isEmpty()) {
    		throw new NotFoundException("Restaurante não encontrado");
    	}
    	
    	Optional<Prato> findPratoById = Prato.findByIdOptional(idPrato);
    	findPratoById.ifPresentOrElse(Prato::delete, () -> {throw new NotFoundException("Prato não encontrado");});
    
    	return Response.status(Status.NO_CONTENT).build();
    }
}