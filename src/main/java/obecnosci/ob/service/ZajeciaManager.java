package obecnosci.ob.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;

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
	
}
