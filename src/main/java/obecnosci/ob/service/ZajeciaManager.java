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
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ZajeciaManager {
	
	@PersistenceContext
	EntityManager em;
	
	/*public void zaplanujZajecia(Prowadzacy instProwadzacego, ){
		
	}*/
	
	// utworznie encji zaj��
	public void zaplanujAutoStart(long idProwadzacego, long idPrzedmiotu, long idGrupy, int ileZajec, Date pierwszeZajecia){
		
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, idProwadzacego);
		Przedmiot przedmiot = em.getReference(Przedmiot.class, idPrzedmiotu);
		Grupa grupa = em.getReference(Grupa.class, idGrupy);
		
		Calendar cal = Calendar.getInstance();
		boolean startCzasDST = cal.getTimeZone().inDaylightTime(pierwszeZajecia);
		
		//         HOUR			DAY		WEEK   
		// 7days = 3600sec *  24hours *7 days  = 604800 seconds = 604800000 ms
		
		// TODO: Dostosowac do zmian czasu - letni/zimowy aby nie wplywalo na zmiane godziny jak teraz (+1 h / -1 h) jaki� If, etc
		for(int i=1; i<=ileZajec; i++){
			Zajecia zajecia = new Zajecia();
			zajecia.setProwadzacy(prowadzacy);
			zajecia.setPrzedmiot(przedmiot);
			zajecia.setGrupa(grupa);
			Date dataTych = new Date(pierwszeZajecia.getTime()+ ((long) 604800000 * (long) i));
			
			// Obsluga zmiany czasu letni/zimowy
			if(startCzasDST){
				if(!cal.getTimeZone().inDaylightTime(dataTych)){
					dataTych = new Date(dataTych.getTime()+3600000);
				}
			} else {
				if(cal.getTimeZone().inDaylightTime(dataTych)){
					dataTych = new Date(dataTych.getTime()-3600000);
				}
			}
			
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
	
public List<Zajecia> pobierzDlaStudenta(long idStudenta){
	List<Grupa> grupy = em.createQuery("select s.grupy from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Przedmiot> przedmioty = em.createQuery("select s.przedmioty from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and z.przedmiot in (:przedmioty) ").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).getResultList();
	// bylo zle zapytanie - poprawiono - wypisuje wszystkie zajecia danego studenta
	
}

public List<Zajecia> pobierzDlaStudentaTerazOdbywajaceSie(long idStudenta){
	List<Grupa> grupy = em.createQuery("select s.grupy from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Przedmiot> przedmioty = em.createQuery("select s.przedmioty from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	Date now = new Date();
	Date kwadrans = new Date(now.getTime()-1200);
	return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and z.przedmiot in (:przedmioty) and z.data >= :kwadrans").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).setParameter("kwadrans", kwadrans).getResultList();
	
}
	
}
