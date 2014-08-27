package web.recdata.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.model.Server;



@Path("/servicos")
public class VerificaServidor {
// est� classe serve para  a verifica��o se o servidor esta online ou n�o
	@GET
    @Path("servidorOnline/")
    @Produces("application/json")
    public Server servidorOnline() {
            
            Server server = new Server();
            server.setOnline(true);
            
            return server;
    }	
}
