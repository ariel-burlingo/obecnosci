package obecnosci.ob.service;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;

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
	
	public void wypiszProwadzacegoZPrzedmiotu(long prowadzacyId, long przedmiotId){
		Prowadzacy prowadzacy = em.getReference(Prowadzacy.class, prowadzacyId);
		Przedmiot przedmiot = em.getReference(Przedmiot.class, przedmiotId);
		List<Przedmiot> przedmioty = prowadzacy.getPrzedmioty();
		przedmioty.remove(przedmiot);
		prowadzacy.setPrzedmioty(przedmioty);
		em.merge(prowadzacy);
	}
	
	public List<Przedmiot> pobierzWszystkie(){
		return em.createQuery("from Przedmiot").getResultList();
	}
	
	public Przedmiot pobierzPoId(long id){
		return (Przedmiot) em.createQuery("select p from Przedmiot p where p.id = :id").setParameter("id", id).getSingleResult();
	}
	
	public void zapiszListeNaPrzedmiot(List<Student> studenty, long id){
		Przedmiot przedmiot = em.getReference(Przedmiot.class, id);
		
		Iterator<Student> istud = studenty.iterator();
		while(istud.hasNext()){
			Student student = em.getReference(Student.class, istud.next().getId());
					
			List<Przedmiot> przedmioty = student.getPrzedmioty();
			przedmioty.add(przedmiot);
			student.setPrzedmioty(przedmioty);
			
			List<Student> studenci = przedmiot.getStudenci();
			studenci.add(student);
			przedmiot.setStudenci(studenci);
			
			em.merge(student);
			em.merge(przedmiot);
		}
	}
	
}
