package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;

//NEW
import obecnosci.ob.domain.Zajecia;
//
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
	//NEW TODO:
	public List<Zajecia> pobierzMojeZajecia(long prowadzacyId){
		List<Zajecia> zajecia = new ArrayList<Zajecia>();
		zajecia = em.createQuery("select z from Zajecia z where z.prowadzacy.id = :prowadzacyId").setParameter("prowadzacyId", prowadzacyId).getResultList();
		return zajecia;
	}
	
	public List<Zajecia> pobierzMojeAktualnieOdbywajaceSieZajecia(long prowadzacyId){
		Date now = new Date();
		Date aktualne = new Date(now.getTime()-7200000);
		//List<Zajecia> zajecia = new ArrayList<Zajecia>();
		return em.createQuery("select z from Zajecia z where z.prowadzacy.id = :prowadzacyId and z.data >= :aktualne and z.data <= :now").setParameter("aktualne", aktualne).setParameter("now", now).setParameter("prowadzacyId", prowadzacyId).getResultList();
		//return zajecia;
		
	}
	
	public List<Student> pobierzMoichStudentow(long idProwadzacego){
		List<Przedmiot> mojePrzedmioty = pobierzMoje(idProwadzacego);
		List<Long> idMP = new ArrayList<Long>();
		List<Student> studenty = em.createQuery("from Student").getResultList();
		List<Student> wybrancy = new ArrayList<Student>();
		Iterator<Student> iter = studenty.iterator();
		
		Iterator<Przedmiot> piter = mojePrzedmioty.iterator();
		while(piter.hasNext()){
			idMP.add(piter.next().getId());
		}
		
		while(iter.hasNext()){
			System.out.println("wszedlem do while");
			Student stud = iter.next();
			Iterator<Przedmiot> p2iter = stud.getPrzedmioty().iterator();
			while(p2iter.hasNext()){
				if(idMP.contains(p2iter.next().getId())){
					wybrancy.add(stud);
					System.out.println("zapisalem gnojka");
					break;
				}
			}
		}
		return wybrancy;
	}
	//
}
