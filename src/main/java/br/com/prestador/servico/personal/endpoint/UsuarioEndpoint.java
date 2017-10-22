package br.com.prestador.servico.personal.endpoint;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prestador.servico.personal.entity.Usuario;
import br.com.prestador.servico.personal.service.IUsuarioService;

@Component
@Path("/usuario")
public class UsuarioEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioEndpoint.class);	
	@Autowired
	private IUsuarioService UsuarioService;
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioDetails() {
		List<Usuario> list = UsuarioService.getAllUsuarios(); 
		return Response.ok(list).build();
	}
	@GET
	@Path("/usuariosProximos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioDetails(long idUsuario) {
		List<Usuario> list = UsuarioService.getAllUsuariosProximos(5d, idUsuario);  
		return Response.ok(list).build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioById(@PathParam("id") Integer id) {
		Usuario Usuario = UsuarioService.getUsuarioById(id);
		return Response.ok(Usuario).build();
	}
	@GET
	@Path("/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioByEmail(@PathParam("email") String email) {
		Usuario Usuario = UsuarioService.getUsuarioByEmail(email);
		return Response.ok(Usuario).build();
	}
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsuario(Usuario Usuario) {
        boolean isAdded = UsuarioService.addUsuario(Usuario);
        if (!isAdded) {
        	logger.info("Usuario already exits.");
	        return Response.status(Status.CONFLICT).build();
        }
        return Response.created(URI.create("/spring-app/Usuario/"+ Usuario.getId())).build();
	}	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateUsuario(Usuario Usuario) {
		UsuarioService.updateUsuario(Usuario);
		return Response.ok(Usuario).build();
	}
	
	@POST
	@Path("/updateCoordenada")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateUsuarioCoordenada(Usuario Usuario) {
		Usuario usr = UsuarioService.getUsuarioByIdFace(Usuario.getIdFace());
		usr.setLati(Usuario.getLati());
		usr.setLongi(Usuario.getLongi());
		UsuarioService.updateUsuario(usr);
		return Response.ok(Usuario).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteUsuario(@PathParam("id") Integer id) {
		UsuarioService.deleteUsuario(id);
		return Response.noContent().build();
	}	
}