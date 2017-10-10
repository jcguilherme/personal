package br.com.prestador.servico.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prestador.servico.personal.dao.IUsuarioDAO;
import br.com.prestador.servico.personal.entity.Usuario;
@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private IUsuarioDAO UsuarioDAO;
	@Override
	public Usuario getUsuarioById(int UsuarioId) {
		Usuario obj = UsuarioDAO.getUsuarioById(UsuarioId);
		return obj;
	}	
	@Override
	public Usuario getUsuarioByEmail(String email) {
		Usuario obj = UsuarioDAO.getUsuarioByEmail(email);
		return obj;
	}	
	
	
	
	@Override
	public List<Usuario> getAllUsuarios(){
		return UsuarioDAO.getAllUsuarios();
	}
	@Override
	public synchronized boolean addUsuario(Usuario Usuario){
       if (UsuarioDAO.UsuarioExists(Usuario.getEmail())) {
    	   return false;
       } else {
    	   UsuarioDAO.addUsuario(Usuario);
    	   return true;
       }
	}
	@Override
	public void updateUsuario(Usuario Usuario) {
		UsuarioDAO.updateUsuario(Usuario);
	}
	@Override
	public void deleteUsuario(int UsuarioId) {
		UsuarioDAO.deleteUsuario(UsuarioId);
	}
}
