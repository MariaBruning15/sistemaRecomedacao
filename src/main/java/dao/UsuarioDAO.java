package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;

public class UsuarioDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");

    public Usuario inserir(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		return usuario;
	}
}

