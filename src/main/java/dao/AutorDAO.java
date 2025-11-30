package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Autor;


public class AutorDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");

    public Autor inserir(Autor autor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(autor);
		em.getTransaction().commit();
		em.close();
		return autor;
	}
}
