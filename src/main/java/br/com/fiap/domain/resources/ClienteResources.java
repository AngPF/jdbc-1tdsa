package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Cliente;
import br.com.fiap.domain.repository.ClienteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteResources {

    @Context
    UriInfo uriInfo;

    private ClienteRepository repo = new ClienteRepository();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<Cliente> clientes = new ArrayList<>();
                clientes = repo.findAll();

        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Cliente cliente = repo.findById(id);
        if (Objects.isNull(cliente)) Response.noContent();

       return Response.ok(cliente).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Cliente c){
        Cliente cliente = repo.persist(c);

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path(String.valueOf(cliente.getId())).build();
        return Response.created().entity(cliente).build();
    }
}
