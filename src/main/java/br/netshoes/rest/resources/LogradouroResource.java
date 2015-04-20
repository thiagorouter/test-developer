package br.netshoes.rest.resources;

import br.netshoes.entity.Logradouro;
import br.netshoes.rest.resources.response.LogradouroResourceResponse;
import br.netshoes.service.LogradouroService;
import br.netshoes.util.ValidadorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/logradouro")
public class LogradouroResource {

    @Autowired
    private LogradouroService logradouroService;

    @Path("cep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public LogradouroResourceResponse getByCEP(@PathParam("cep") String cep) {

        boolean isValid = ValidadorUtil.isValidCEP(cep);

        if (isValid) {

            Logradouro logradouro;

            StringBuilder stringBuilder = new StringBuilder(cep);

            for (int i = cep.length(); i != 0; i--) {

                stringBuilder.setCharAt(i - 1, '0');

                logradouro = this.logradouroService.byCep(stringBuilder.toString());

                if (null != logradouro) {
                    return new LogradouroResourceResponse(logradouro);
                }

            }

            return new LogradouroResourceResponse(
                    "Não foi encontrado logradouro com o CEP informado.");
        } else {
            return new LogradouroResourceResponse("CEP Inválido");
        }

    }

    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Logradouro> list() {

        return logradouroService.findAll();

    }

    @Path("list/{quantidadedeRegistros}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Logradouro> list(@PathParam("quantidadedeRegistros") Integer maxResult) {

        final List<Logradouro> logradouros = logradouroService.findAll(maxResult);

        return logradouros;

    }

}
