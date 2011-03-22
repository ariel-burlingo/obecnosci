package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ZajeciaManager {
	
	@PersistenceContext
	EntityManager em;
	
	/*public void zaplanujZajecia(Prowadzacy instProwadzacego, ){
		
	}*/
	
	// utworznie encji zajêæ
	// dTyg 1 - poniedizalek, 7 niedziela
	public void zaplanujAutoStart(long idProwadzacego, int ileZajec, Date pierwszeZajecia){
		//pierwszeZajecia.
		//Calendar pZ = Calendar.getInstance();
		//pZ.setTime(pierwszeZajecia);
		
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, idProwadzacego);
		//         HOUR			DAY		WEEK   
		// 7days = 3600sec *  24hours *7 days  = 604800 seconds = 604800000 ms
		
		//for(int i=1; i<=ileZajec; i++){
		int i=1;
			Zajecia zajecia = new Zajecia();
			zajecia.setProwadzacy(prowadzacy);
			Date dataTych = new Date(pierwszeZajecia.getTime()+ ((long) 604800000 * (long) i));
			zajecia.setData(dataTych);
			em.persist(zajecia);
		//}
		
		
		
	}
	
	//TODO: Set method arguments.
	public void usunZajecia(){
		//TODO: Implement the method stub.
	}
	public List<Zajecia> pobierzWszystkie(){
		return em.createQuery("from Zajecia").getResultList();
	}
	
}
