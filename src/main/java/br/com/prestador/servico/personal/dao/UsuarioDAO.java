package br.com.prestador.servico.personal.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.prestador.servico.personal.entity.Usuario;
import br.com.prestador.servico.personal.util.Distancia;

@Transactional
@Repository
public class UsuarioDAO implements IUsuarioDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Usuario getUsuarioById(long UsuarioId) {
		return entityManager.find(Usuario.class, UsuarioId);
	}
	@Override
	public Usuario getUsuarioByEmail(String email) {
		String hql = "FROM Usuario as usr WHERE usr.email = ? ";
		List<Usuario> lista= entityManager.createQuery(hql).setParameter(1, email)
		              .getResultList();
		
		return !lista.isEmpty()?lista.get(0):null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getAllUsuarios() {
		String hql = "FROM Usuario as usr ORDER BY usr.id";
		return (List<Usuario>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addUsuario(Usuario Usuario) {
		entityManager.persist(Usuario);
	}
	@Override
	public void updateUsuario(Usuario usuario) {
		Usuario usr = getUsuarioById(usuario.getId());
		usr.setLati(usuario.getLati());
		usr.setLongi(usuario.getLongi());
		usr.setToken(usuario.getToken());
		//artcl.setTitle(Usuario.getTitle());
		//artcl.setCategory(Usuario.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteUsuario(int UsuarioId) {
		entityManager.remove(getUsuarioById(UsuarioId));
	}
	@Override
	public boolean UsuarioExists(String email) {
		String hql = "FROM Usuario as usr WHERE usr.email = ? ";
		int count = entityManager.createQuery(hql).setParameter(1, email)
		              .getResultList().size();
		return count > 0 ? true : false;
	}
	@Override
	public Usuario getUsuarioByIdFace(String idFace) {
		String hql = "FROM Usuario as usr WHERE usr.idFace = ? ";
		List<Usuario> lista= entityManager.createQuery(hql).setParameter(1, idFace)
		              .getResultList();
		
		return !lista.isEmpty()?lista.get(0):null;
	}
	
	public List<Usuario> getAllUsuariosProximos(double distanciaKm, long idUsuario) {
		String hql = "FROM Usuario as usr ORDER BY usr.id";
		Usuario usr = entityManager.find(Usuario.class, idUsuario);
		List<Usuario>usuarios = (List<Usuario>) entityManager.createQuery(hql).getResultList();
		List<Usuario>usuariosProximos = new ArrayList<Usuario>();
		for(Usuario usrbd : usuarios){
			if(Distancia.distance(usr.getLati(), usrbd.getLati(), usr.getLongi(), usrbd.getLongi(), 0, 0) <= distanciaKm){
				usuariosProximos.add(usrbd);
			}
		}
		return usuariosProximos;
	}
}
