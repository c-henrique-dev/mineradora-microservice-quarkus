package br.com.henrique.mineradora.client;

import br.com.henrique.mineradora.dto.CurrencyPriceDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/last")
@RegisterRestClient
@ApplicationScoped
public interface CurrencyPriceClient {
    @GET
    @Path("/{pair}")
    CurrencyPriceDto getPriceByPair(@PathParam("pair") String pair);

}
