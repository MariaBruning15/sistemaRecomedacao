package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Livro;

public class LivroDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");

    public Livro inserir(Livro livro) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		em.close();
		return livro;
	}
}
