package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Obecnosci;
import obecnosci.ob.domain.Obecnosci.Typ_Obecnosci;
import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.utils.StudentZajecia;

@Stateless
public class ObecnosciManager {

	@Inject
	ProwadzacyManager prowadzacyManager;
	@Inject
	ZajeciaManager zajeciaManager;	
	@PersistenceContext
	EntityManager em;

	public void zapiszObecnosc(Long IdStudenta, Long IdZajec) {
		Obecnosci obecnosc = new Obecnosci();
		Student student = em.getReference(Student.class, IdStudenta);
		Zajecia zajecia = em.getReference(Zajecia.class, IdZajec);
		obecnosc.setStudent(student);
		obecnosc.setZajecia(zajecia);
		obecnosc.setTyp(Typ_Obecnosci.NIEPOTWIERDZONA);
		em.persist(obecnosc);
	}

	public void zatwierdzObecnosci(Long IdObecnosci) {
		Obecnosci obecnosc = em.getReference(Obecnosci.class, IdObecnosci);
		obecnosc.setTyp(Typ_Obecnosci.POTWIERDZONA);
		em.merge(obecnosc);
	}

	public void usprawiedliwNieObecnosc(long IdStudenta, long IdZajec) {		
		Obecnosci obecnosc = new Obecnosci();
		Student student = em.getReference(Student.class, IdStudenta);
		Zajecia zajecia = em.getReference(Zajecia.class, IdZajec);
		obecnosc.setStudent(student);
		obecnosc.setZajecia(zajecia);
		obecnosc.setTyp(Typ_Obecnosci.USPRAWIEDLIWIONA);
		em.persist(obecnosc);
		em.merge(obecnosc);
	}

	public List<Obecnosci> pobierzWszystkie() {
		return em.createQuery("from Obecnosci").getResultList();
	}
	
	public List<Obecnosci> pobierzMoje(long id){
		
		return em.createQuery("select s from Obecnosci s where s.student.id = :id").setParameter("id", id).getResultList();
	
	}
	//NEW
	public List<Obecnosci> pobierzObecnosciZZajec(long ZAJECIA_ID){
	
	return  em.createQuery("select o from Obecnosci o where o.zajecia.id = :ZAJECIA_ID").setParameter("ZAJECIA_ID", ZAJECIA_ID).getResultList();
	}
	//
	
	// EKSPORT RAPORTU HTML/PDF
	public List<StudentZajecia> daneDoRaportu(long przedmiotId, long prowadzacyId, long grupaId){
		System.out.println("WESZLEM DO RAPORTU ");
	
		List<StudentZajecia> studentyZajecia = new ArrayList<StudentZajecia>();
		
		// pobranie przedmiotow prowadzacego
		//List<Przedmiot> przedmiotyProwadzacego = prowadzacyManager.pobierzMoje(prowadzacyId);
		// pobieranei zajec zgodnie z podana grupa i prowadzacym;
		List<Zajecia> zajecia = new ArrayList<Zajecia>();
		
		zajecia = em.createQuery("select z from Zajecia z where z.prowadzacy.id = :prowadzacyId and z.grupa.id = :grupaId and z.przedmiot.id = :przedmiotId").setParameter("prowadzacyId", prowadzacyId).setParameter("grupaId", grupaId).setParameter("przedmiotId", przedmiotId).getResultList();
		
		// pobranie moich studentow
		
		List<Student> studenty = em.createQuery("from Student").getResultList();
		List<Student> wybrancy = new ArrayList<Student>();
		Iterator<Student> iter = studenty.iterator();
				
		while(iter.hasNext()){
			System.out.println("wszedlem do while");
			Student stud = iter.next();
			Iterator<Przedmiot> p2iter = stud.getPrzedmioty().iterator();
			while(p2iter.hasNext()){
				if(p2iter.next().getId()==przedmiotId){
					wybrancy.add(stud);
					System.out.println("zapisalem gnojka");
					break;
				}
			}
		}
			
		// iterowanie po moich studentach
		Iterator<Student> istud = wybrancy.iterator();
		while(istud.hasNext()){
				Student aktualny = istud.next();
				List<String> obecnosci = new ArrayList<String>();
				List<Zajecia> sZajOb =  pobierzObecnosciDoRaportu(aktualny.getId(), grupaId, przedmiotId);
				List<Zajecia> sZajNOb = pobierzNieObecnosciDoRaportu(aktualny.getId(), grupaId, przedmiotId);
				// iterowanie po wszystkich zajeciach celem ³uskania nieobecnosi/obecnosci
				Iterator<Zajecia> izaj = zajecia.iterator();
				System.out.print(aktualny.getImie()+" ");
				while(izaj.hasNext()){
					Zajecia aktZaj = izaj.next();
					if(sZajOb.contains(aktZaj)){
						Obecnosci obTyp = (Obecnosci) em.createQuery("select o from Obecnosci as o where o.student.id= :studentId and o.zajecia = :zaj").setParameter("studentId", aktualny.getId()).setParameter("zaj", aktZaj).getSingleResult();
						// tutaj bedzie logika sprawdzajaca czy Usprawiedliwiony czy obecny
						if(obTyp.getTyp() == Typ_Obecnosci.POTWIERDZONA){
							obecnosci.add("O");
							System.out.print("O");
						}
						if(obTyp.getTyp() == Typ_Obecnosci.NIEPOTWIERDZONA){
							obecnosci.add("!");
							System.out.print("!");
						}
						if(obTyp.getTyp() == Typ_Obecnosci.USPRAWIEDLIWIONA){
							obecnosci.add("U");
							System.out.print("U");
						}
					}
					if(sZajNOb.contains(aktZaj)){
						obecnosci.add("N");
						System.out.print("N");
					}
				}
				
				
				StudentZajecia studZaj = new StudentZajecia();
				studZaj.setStudent(aktualny);
				// zamiana na tablice
				String wObecnosci[] = new String[obecnosci.size()];
				Iterator<String> obIter = obecnosci.iterator();
				int i = 0;
				while(obIter.hasNext()){
					wObecnosci[i] = obIter.next();
					i++;
				}
				
				studZaj.setObecnosci(wObecnosci);
				
				// dopisanie do glownej listy
				
				studentyZajecia.add(studZaj);
				
				System.out.println(" ");
		}
		
		return studentyZajecia;
	}
	
	
	public List<Zajecia> pobierzObecnosciDoRaportu(long idStudenta,long idGrupy, long idPrzedmiotu){
		List<Zajecia> obecnosci = new ArrayList<Zajecia>();
		obecnosci = em.createQuery("select o.zajecia from Obecnosci o where o.student.id = :idStudenta and o.zajecia.grupa.id = :grupaId and o.zajecia.przedmiot.id = :przedmiotId")
		.setParameter("idStudenta", idStudenta).setParameter("grupaId", idGrupy).setParameter("przedmiotId", idPrzedmiotu).getResultList();
		return obecnosci;
	}
	
	public List<Zajecia> pobierzNieObecnosciDoRaportu(long idStudenta,long idGrupy, long idPrzedmiotu){
		List<Zajecia> obecnosci = em.createQuery("select o.zajecia from Obecnosci o where o.student.id = :idStudenta and o.zajecia.grupa.id = :grupaId and o.zajecia.przedmiot.id = :przedmiotId")
		.setParameter("idStudenta", idStudenta).setParameter("grupaId", idGrupy).setParameter("przedmiotId", idPrzedmiotu).getResultList();
		List<Zajecia> nieobecnosci = new ArrayList<Zajecia>();
		if(obecnosci.size() > 0){
			nieobecnosci = em.createQuery("from Zajecia as z where z.grupa.id = :grupaId and z.przedmiot.id = :przedmiotId and not z in (:obecnosci) ")
			.setParameter("grupaId", idGrupy).setParameter("przedmiotId", idPrzedmiotu).setParameter("obecnosci", obecnosci).getResultList();
		} else {
			nieobecnosci = em.createQuery("from Zajecia as z where z.grupa.id = :grupaId and z.przedmiot.id = :przedmiotId")
			.setParameter("grupaId", idGrupy).setParameter("przedmiotId", idPrzedmiotu).getResultList();
		
		} 
		return nieobecnosci;
		
	}

}
