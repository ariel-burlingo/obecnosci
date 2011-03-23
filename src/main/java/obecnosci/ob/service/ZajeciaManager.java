package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ZajeciaManager {
	
	@PersistenceContext
	EntityManager em;
	
	/*public void zaplanujZajecia(Prowadzacy instProwadzacego, ){
		
	}*/
	
	// utworznie encji zajêæ
	public void zaplanujAutoStart(long idProwadzacego, long idPrzedmiotu, long idGrupy, int ileZajec, Date pierwszeZajecia){
		
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, idProwadzacego);
		Przedmiot przedmiot = em.getReference(Przedmiot.class, idPrzedmiotu);
		Grupa grupa = em.getReference(Grupa.class, idGrupy);
		//         HOUR			DAY		WEEK   
		// 7days = 3600sec *  24hours *7 days  = 604800 seconds = 604800000 ms
		
		// TODO: Dostosowac do zmian czasu - letni/zimowy aby nie wplywalo na zmiane godziny jak teraz (+1 h / -1 h) jakiœ If, etc
		for(int i=1; i<=ileZajec; i++){
			Zajecia zajecia = new Zajecia();
			zajecia.setProwadzacy(prowadzacy);
			zajecia.setPrzedmiot(przedmiot);
			zajecia.setGrupa(grupa);
			Date dataTych = new Date(pierwszeZajecia.getTime()+ ((long) 604800000 * (long) i));
			zajecia.setData(dataTych);
			em.persist(zajecia);
		}
		
	}
	
	
	public void zmienCzasRozpoczecia(long idProwadzacego, long idZajec, Date start){
		Zajecia zajecia = em.getReference(Zajecia.class, idZajec);
		zajecia.setData(start);
		em.merge(zajecia);		
	}
	
	
	public void usunZajecia(long idZajec){
		Zajecia zajecia = em.getReference(Zajecia.class, idZajec);
		em.remove(zajecia);		
	}
	public List<Zajecia> pobierzWszystkie(){
		return em.createQuery("from Zajecia").getResultList();
	}
	
}
