package com.github.jonataslaet.lafood.cadastro.resources;

import java.util.List;
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

import com.github.jonataslaet.lafood.cadastro.entities.Restaurante;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    public List<Restaurante> buscarRestaurantes() {
        return Restaurante.listAll();
    }
    
    @POST
    @Transactional
    public Response adicionar(Restaurante entity) {
    	entity.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Restaurante entity) {
    	Optional<Restaurante> findById = Restaurante.findByIdOptional(id);
    	if (findById.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = findById.get();
    	restaurante.proprietario = entity.proprietario;
    	restaurante.persist();
    	return Response.status(Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
    	Optional<Restaurante> findById = Restaurante.findByIdOptional(id);
    	if (findById.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = findById.get();
    	restaurante.delete();
    	return Response.status(Status.NO_CONTENT).build();
    }
}