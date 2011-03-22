package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;

@Stateless
public class ProwadzacyManager {

	@PersistenceContext
	EntityManager em;
	
	public void dodajProwadzacego(String imie, String nazwisko, String daneKontaktowe, String stronaDomowa){
		Prowadzacy prowadzacy = new Prowadzacy();
		prowadzacy.setImie(imie);
		prowadzacy.setNazwisko(nazwisko);
		prowadzacy.setDaneKontaktowe(daneKontaktowe);
		prowadzacy.setStronaDomowa(stronaDomowa);
		em.persist(prowadzacy);
		
	}
	
	public void usunProwadzacego(Prowadzacy instancja){
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, instancja.getId());
		em.remove(prowadzacy);
	}
	
	public List<Prowadzacy> pobierzWszystkie(){
		return em.createQuery("from Prowadzacy").getResultList();
	}
	
}
