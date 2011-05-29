package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Obecnosci;
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
	
	// utworznie encji zajêæ
	public void zaplanujAutoStart(long idProwadzacego, long idPrzedmiotu, long idGrupy, int ileZajec, Date pierwszeZajecia){
		
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, idProwadzacego);
		Przedmiot przedmiot = em.getReference(Przedmiot.class, idPrzedmiotu);
		Grupa grupa = em.getReference(Grupa.class, idGrupy);
		
		Calendar cal = Calendar.getInstance();
		boolean startCzasDST = cal.getTimeZone().inDaylightTime(pierwszeZajecia);
		
		//         HOUR			DAY		WEEK   
		// 7days = 3600sec *  24hours *7 days  = 604800 seconds = 604800000 ms
		
		// TODO: Dostosowac do zmian czasu - letni/zimowy aby nie wplywalo na zmiane godziny jak teraz (+1 h / -1 h) jakiœ If, etc
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

public List<Zajecia> pobierzDlaStudentaZajeciaNaKtorychBylNieObecny(long idStudenta, List<Przedmiot> przedmiotyProwadzacego){
	/*
	List<Grupa> grupy = em.createQuery("select s.grupy from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Przedmiot> przedmiotyStud = em.createQuery("select s.przedmioty from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	if(przedmiotyStud.size()>0 && przedmiotyProwadzacego.size() > 0){
		return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and (:przedmiotyStud) in (:moje) ").setParameter("grupy", grupy).setParameter("przedmiotyStud",przedmiotyStud).setParameter("moje", przedmiotyProwadzacego).getResultList();
	} else {
		return new ArrayList<Zajecia>();
	}*/
	
	List<Grupa> grupy = em.createQuery("select s.grupy from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Przedmiot> przedmioty = em.createQuery("select s.przedmioty from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Zajecia> obecnosci = em.createQuery("select o.zajecia from Obecnosci o where o.student.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	// escape from null pointers code
	if(grupy.size() > 0 && przedmioty.size() > 0 && przedmiotyProwadzacego.size() >0){
		if(obecnosci.size() > 0){
			return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and z.przedmiot in (:przedmioty) and z.przedmiot in (:przedmiotyProw) and not z in (:obecnosci) ").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).setParameter("przedmiotyProw", przedmiotyProwadzacego).setParameter("obecnosci", obecnosci).getResultList();
		} else {
			return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and z.przedmiot in (:przedmioty)").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).getResultList();
		}
	} else {
		return new ArrayList<Zajecia>();
	}
}

public List<Zajecia> pobierzDlaStudentaTerazOdbywajaceSie(long idStudenta){
	// pod warunkiem ze nie zapisal juz obecnosci
	List<Grupa> grupy = em.createQuery("select s.grupy from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Przedmiot> przedmioty = em.createQuery("select s.przedmioty from Student s where s.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	List<Zajecia> ozaj = em.createQuery("select o.zajecia from Obecnosci o where o.student.id = :idStudenta").setParameter("idStudenta", idStudenta).getResultList();
	System.out.println("Ozaj size: "+ozaj.size());
	Date now = new Date();
	Date kwadrans = new Date(now.getTime()-900000);
	System.out.println("NOW      : "+now.toString());
	System.out.println("Kwadrans : "+kwadrans.toString());
	if(ozaj.size() > 0){
		return em.createQuery("from Zajecia as z where z not in (:ozaj) and z.grupa in (:grupy) and z.przedmiot in (:przedmioty) and z.data >= :kwadrans and z.data <= :now").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).setParameter("ozaj", ozaj).setParameter("now", now).setParameter("kwadrans", kwadrans).getResultList();
	} else { // inaczej brak obecnoœci wywali b³¹d!
		//return new ArrayList<Zajecia>();
		return em.createQuery("from Zajecia as z where z.grupa in (:grupy) and z.przedmiot in (:przedmioty) and z.data >= :kwadrans and z.data <= :now").setParameter("grupy", grupy).setParameter("przedmioty", przedmioty).setParameter("now", now).setParameter("kwadrans", kwadrans).getResultList();
	}
}
	
}
