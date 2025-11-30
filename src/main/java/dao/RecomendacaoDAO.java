package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Recomendacao;

public class RecomendacaoDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");

    public Recomendacao inserir(Recomendacao recomendacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(recomendacao);
		em.getTransaction().commit();
		em.close();
		return recomendacao;
	}
}
