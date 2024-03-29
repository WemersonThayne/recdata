package web.recdata.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import web.recdata.controller.ItemController;
import br.edu.ifpb.recdata.entidades.Item;

@Path("/item")
public class ItemResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Item> listarTodos() throws SQLException {
		return new ItemController().listarTodos();
	}
	
	@POST
	@Path("/consultarItens")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Item> consultarItens(Item item) throws SQLException {
		return new ItemController().consultarItens(item);
	}
	
	@POST
	@Path("/criar")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response creat(Item item) {
		return new ItemController().creat(item);
	}

	@POST
	@Path("/buscar")
	@Consumes("application/json")
	@Produces("application/json")
	public Item readById(Item item) {
		return new ItemController().readById(item);
	}
	
	@Path("/atualizar")
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String update(Item item) {
		return new ItemController().update(item);
	}

	@Path("/deletar")
	@DELETE
	@Consumes("application/json")
	@Produces("text/plain")
	public String delete(Item item) {
		return new ItemController().delete(item);
	}
}
