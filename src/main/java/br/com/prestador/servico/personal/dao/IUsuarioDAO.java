package br.com.prestador.servico.personal.dao;
import java.util.List;

import br.com.prestador.servico.personal.entity.Usuario;
public interface IUsuarioDAO {
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuarioById(long UsuarioId);
    public Usuario getUsuarioByEmail(String email);
    public void addUsuario(Usuario Usuario);
    public void updateUsuario(Usuario Usuario);
    public void deleteUsuario(int UsuarioId);
    public boolean UsuarioExists(String email);
}
 