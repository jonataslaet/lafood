package com.github.jonataslaet.lafood.cadastro.resources;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public void adicionar(Restaurante entity) {
    	entity.persist();
    }
}