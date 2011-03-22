package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ZajeciaManager {
	@PersistenceContext
	EntityManager em;
	
	public void zaplanujZajecia(Prowadzacy instProwadzacego, Przedmiot instPrzedmiotu){
		// TODO: Implement the method stub.
	}
	
	public void autoStart(){
		//TODO: Implement the method stub.		
	}
	
	//TODO: Set method arguments.
	public void usunZajecia(){
		//TODO: Implement the method stub.
	}
	public List<Zajecia> pobierzWszystkie(){
		return em.createQuery("from Zajecia").getResultList();
	}
	
}
