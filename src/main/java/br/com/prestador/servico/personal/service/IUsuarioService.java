package br.com.prestador.servico.personal.service;

import java.util.List;

import br.com.prestador.servico.personal.entity.Usuario;

public interface IUsuarioService {
     public List<Usuario> getAllUsuarios();
     public Usuario getUsuarioById(int UsuarioId);
     public Usuario getUsuarioByEmail(String email);
     public boolean addUsuario(Usuario Usuario);
     public void updateUsuario(Usuario Usuario);
     public void deleteUsuario(int UsuarioId);
}
