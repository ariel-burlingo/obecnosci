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

	/** Udostêpnia metody EJB studenta.
	 * 
	 * dodajStudenta(int index, String imie, String nazwisko, String haslo)
	 * usunStudenta(Student instancja)
	 * zmienDaneStudenta(Student instancja, String imie, String nazwisko, String haslo)
	 * 
	 * TODO: Funkcja mieszaj¹ca dla hase³ co by nie zdupiæ jak 'portal zapisy' - mo¿e poni¿sza ?
	 * LINK: http://www.java2s.com/Tutorial/Java/0490__Security/CheckpasswordsaltbasedonMD5.htm
	 */
	
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
	
	public void usunStudenta(Student instancja){
		Student student = em.getReference(Student.class, instancja.getId());
		em.remove(student);		
	}
	
	public void zmienDaneStudenta(Student instancja, String imie, String nazwisko, String haslo){
		Student student = em.getReference(Student.class, instancja.getId());
		student.setImie(imie);
		student.setNazwisko(nazwisko);
		// TODO: ZHASZOWAÆ KUR.. TRZEBA. Bo inczej bêdzie pizda jak z portalem zapisów i go³ymi has³ami!		
		student.setHaslo(haslo);
		
		// zapis
		em.merge(student);
	}
}
