package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Grupa;

@Stateless
public class GrupaManager {

	@PersistenceContext
	EntityManager em;

	public void dodajGrupe(String informacje) {
		Grupa grupa = new Grupa();
		grupa.setInformacje(informacje);
		em.persist(grupa);
	}

	public void usunGrupe(long idGrupy) {
		Grupa grupa = em.getReference(Grupa.class, idGrupy);
		em.remove(grupa);
	}

	public void edytujGrupe(Long id, String informacje) {
		Grupa grupa = em.getReference(Grupa.class, id);
		grupa.setInformacje(informacje);
		em.merge(grupa);
	}

	public List<Grupa> pobierzWszystkie() {
		return em.createQuery("from Grupa").getResultList();
	}
}
