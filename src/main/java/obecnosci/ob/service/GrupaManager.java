package obecnosci.ob.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Grupa;

@Stateless
public class GrupaManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void dodajGrupe(String informacje){
		Grupa grupa = new Grupa();
		grupa.setInformacje(informacje);
		em.persist(grupa);
	}
	
	public void usunGrupe(Grupa instancja){
		Grupa grupa = em.getReference(Grupa.class, instancja.getId());
		em.remove(grupa);
	}
	
	public void zmienDaneGrupy(Grupa instancja, String informacje){
		Grupa grupa = em.getReference(Grupa.class, instancja.getId());
		grupa.setInformacje(informacje);
		em.merge(grupa);
	}
}
