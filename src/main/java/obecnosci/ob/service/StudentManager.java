package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Grupa;

@Stateless
public class StudentManager {

	@PersistenceContext
	EntityManager em;
	
	public void dodajStudenta(int index, String imie, String nazwisko, String haslo){
		Student student = new Student();
		student.setIndex(index);
		student.setImie(imie);
		student.setNazwisko(nazwisko);
		// TODO: ZHASZOWAÆ KUR.. TRZEBA. Bo inczej bêdzie pizda jak z portalem zapisów i go³ymi has³ami!
		student.setHaslo(haslo); 
		
		// grupy
		List<Grupa> grupy = new ArrayList<Grupa>();
		student.setGrupy(grupy);
		
		// zapis
		em.persist(student);
	}
	
	public void usunStudenta(){
		
		
	}
}
