package br.netshoes.rest.resources;

import br.netshoes.entity.Endereco;
import br.netshoes.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Endereco read(@PathParam("id") Long id) {

        Endereco newsEntry = this.enderecoService.find(id);
        if (newsEntry == null) {
            throw new WebApplicationException(404);
        }
        return newsEntry;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Endereco create(final Endereco endereco) {

        return this.enderecoService.save(endereco);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Endereco update(@PathParam("id") Long id, Endereco endereco) {
        return this.enderecoService.save(endereco);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public void delete(@PathParam("id") Long id) {
        this.enderecoService.delete(id);
    }

}
