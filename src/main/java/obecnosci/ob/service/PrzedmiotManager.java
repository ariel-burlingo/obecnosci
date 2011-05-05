package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;

@Stateless
public class PrzedmiotManager {
	@PersistenceContext
	EntityManager em;
	
	public void dodajPrzedmiot(String nazwa, String inneDane){
		Przedmiot przedmiot = new Przedmiot(); 	
		przedmiot.setNazwa(nazwa);
		przedmiot.setinneDane(inneDane);
		em.persist(przedmiot);
		
	}
	
	public void usunPrzedmiot(Przedmiot instancja){
		Przedmiot przedmiot = em.getReference(Przedmiot.class, instancja.getId());
		em.remove(przedmiot);
		
	}
	
	public void przypiszPrzedmiotDoProwadzacego(long prowadzacyId, long przedmiotId){
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, prowadzacyId);
		Przedmiot przedmiot = em.getReference(Przedmiot.class, przedmiotId);
		List<Przedmiot> przedmioty = prowadzacy.getPrzedmioty();
		przedmioty.add(przedmiot);
		prowadzacy.setPrzedmioty(przedmioty);
		em.merge(prowadzacy);
		
	}
	
	public List<Przedmiot> pobierzWszystkie(){
		return em.createQuery("from Przedmiot").getResultList();
	}
	
}
