package br.com.prestador.servico.personal.teste;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.prestador.servico.personal.entity.Usuario;

public class JerseyClient {
	private static final String urlBase = "http://localhost:8080/personal";
	public void getUsuarioDetails() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario");
		WebTarget details = base.path("details");
		List<Usuario> list = details.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Usuario>>() {});
		
	    list.stream().forEach(Usuario -> 
	        System.out.println(Usuario.getId()+", "+ Usuario.getEmail()));
	    
	    client.close();
	}
	public void getUsuarioById(int UsuarioId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario");
		WebTarget UsuarioById = base.path("{id}").resolveTemplate("id", UsuarioId);
		Usuario Usuario = UsuarioById.request(MediaType.APPLICATION_JSON)
				.get(Usuario.class);
		
        System.out.println(Usuario.getId()+", "+ Usuario.getEmail());
        
	    client.close();
	}
	
	
	public Usuario getUsuarioByEmail(String email) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario/email");
		WebTarget UsuarioById = base.path("{email}").resolveTemplate("email", email);
		Usuario Usuario = UsuarioById.request(MediaType.APPLICATION_JSON)
				.get(Usuario.class);
		
        System.out.println(Usuario.getId()+", "+ Usuario.getEmail());
        
	    client.close();
	    return Usuario;
	}
	
	public void addUsuario(Usuario Usuario) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario");
		WebTarget add = base.path("add");
		Response response = add.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(Usuario));
		
		System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
        
	    client.close();
	}
	public void updateUsuario(Usuario Usuario) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario");
		WebTarget update = base.path("update");
		Response response = update.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(Usuario));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		Usuario resUsuario = response.readEntity(Usuario.class);
		System.out.println(resUsuario.getId()+", "+ resUsuario.getEmail());
        
	    client.close();
	}
	
	public void updateUsuarioCoordenada(Usuario Usuario) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/usuario");
		WebTarget update = base.path("updateCoordenada");
		Response response = update.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(Usuario));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		Usuario resUsuario = response.readEntity(Usuario.class);
		System.out.println(resUsuario.getId()+", "+ resUsuario.getEmail());
        
	    client.close();
	}
	public void deleteUsuario(int UsuarioId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target(urlBase+"/Usuario");
		WebTarget deleteById = base.path("{id}").resolveTemplate("id", UsuarioId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON)
				.delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		if(response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}
        
	    client.close();
	}
	
	
	
	static JerseyClient jerseyClient = null;
	public static void main(String[] args) {
		jerseyClient = new JerseyClient();
		jerseyClient.inserirRegistros();
		//jerseyClient.listarRegistros();
		Usuario usr = new Usuario();
		usr.setIdFace("xxx");
		usr.setLati(999);
		usr.setLongi(888);
		jerseyClient.updateUsuarioCoordenada(usr);
	}
	
	
	
	public void inserirRegistros(){
		
		Usuario Usuario = new Usuario();
		Usuario.setEmail("um@teste");
		Usuario.setIdFace("xxx");
		jerseyClient.addUsuario(Usuario);
		
		Usuario = new Usuario();
		Usuario.setEmail("dois@teste");
		Usuario.setIdFace("yyy");
		jerseyClient.addUsuario(Usuario);
		
		Usuario = new Usuario();
		Usuario.setEmail("tres@teste");
		Usuario.setIdFace("zzz");
		jerseyClient.addUsuario(Usuario);
		
	}
	
	public void listarRegistros(){
		jerseyClient.getUsuarioDetails();
	}
	
	public void atualizaRegistros(){
		Usuario Usuario= jerseyClient.getUsuarioByEmail("tres@teste");
		Usuario.setLati(100);
		Usuario.setLongi(100);
		jerseyClient.updateUsuario(Usuario);
	}
}
