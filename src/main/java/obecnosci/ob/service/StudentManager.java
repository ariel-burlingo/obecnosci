package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Przedmiot;
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
	
	public Student zaloguj(int index, String haslo){
		Student student = (Student) em.createQuery("select s from Student s where s.index = :index and s.haslo = :haslo").setParameter("index", index).setParameter("haslo", haslo).getSingleResult();
		return student;
	}
	
		
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
	
	public void zmienDaneStudenta(long id, String imie, String nazwisko, String haslo){
		Student student = em.getReference(Student.class, id);
		student.setImie(imie);
		student.setNazwisko(nazwisko);
		// TODO: ZHASZOWAÆ KUR.. TRZEBA. Bo inczej bêdzie pizda jak z portalem zapisów i go³ymi has³ami!		
		student.setHaslo(haslo);
		
		// zapis
		em.merge(student);
	}
	
	public void przypiszDoGrupy(Student instancja, long id){
		Student student = em.getReference(Student.class, instancja.getId());
		Grupa grupa = em.getReference(Grupa.class, id);
		
		List<Grupa> grupy = student.getGrupy();
		grupy.add(grupa);
		student.setGrupy(grupy);
		
		List<Student> studenci = grupa.getStudenci();
		studenci.add(student);
		grupa.setStudenci(studenci);
		
		em.merge(student);
		em.merge(grupa);
	}
	
	public void zapiszNaPrzedmiot(Student instancja, long id){
		Student student = em.getReference(Student.class, instancja.getId());
		Przedmiot przedmiot = em.getReference(Przedmiot.class, id);
		
		List<Przedmiot> przedmioty = student.getPrzedmioty();
		przedmioty.add(przedmiot);
		student.setPrzedmioty(przedmioty);
		
		List<Student> studenci = przedmiot.getStudenci();
		studenci.add(student);
		przedmiot.setStudenci(studenci);
		
		em.merge(student);
		em.merge(przedmiot);
		
	}
	
	// POBIERANIE SQL
	
	public List<Student> pobierzWszystkich(){
		return em.createQuery("from Student").getResultList();
	}
	
	public List<Grupa> pobierzGrupy(long id){
		return em.getReference(Student.class, id).getGrupy();
	}
}
