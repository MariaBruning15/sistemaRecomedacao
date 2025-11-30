package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Avaliacao;


public class AvaliacaoDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");

    public Avaliacao inserir(Avaliacao avaliacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(avaliacao);
		em.getTransaction().commit();
		em.close();
		return avaliacao;
	}
}
