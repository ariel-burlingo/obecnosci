package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;


@Stateless
public class ProwadzacyManager {

	@PersistenceContext
	EntityManager em;
	
	public Prowadzacy zaloguj(String login, String haslo){
		Prowadzacy prowadzacy = (Prowadzacy) em.createQuery("select s from Prowadzacy s where s.login = :login and s.password = :password").setParameter("login", login).setParameter("password", haslo).getSingleResult();
		return prowadzacy;
	}
	
	public void dodajProwadzacego(String imie, String nazwisko, String login, String daneKontaktowe, String stronaDomowa, String password){
		Prowadzacy prowadzacy = new Prowadzacy();
		prowadzacy.setImie(imie);
		prowadzacy.setNazwisko(nazwisko);
		prowadzacy.setLogin(login);
		prowadzacy.setDaneKontaktowe(daneKontaktowe);
		prowadzacy.setStronaDomowa(stronaDomowa);
		prowadzacy.setPassword(password);
		em.persist(prowadzacy);
		
	}
	public void modyfikujProwadzacego ( long id, String daneKontaktowe, String stronaDomowa){
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, id);
		prowadzacy.setDaneKontaktowe(daneKontaktowe);
		prowadzacy.setStronaDomowa(stronaDomowa);
		em.merge(prowadzacy);
	}
	
	public void usunProwadzacego(Prowadzacy instancja){
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, instancja.getId());
		em.remove(prowadzacy);
	}
	
	public List<Prowadzacy> pobierzWszystkie(){
		return em.createQuery("from Prowadzacy").getResultList();
	}
	
	public List<Przedmiot> pobierzMoje(long id){
		List<Przedmiot> przedmioty = new ArrayList<Przedmiot>();
		List<Przedmiot> deep = new ArrayList<Przedmiot>();
		// petla ktora kopiuje z referencji do stworzonego obiektu wiersz po wierszu
		przedmioty = em.find(Prowadzacy.class, id).getPrzedmioty();
		Iterator<Przedmiot> iter = przedmioty.iterator();
		while(iter.hasNext()){
			deep.add(iter.next());
		}
		return deep;
	}
	
}
