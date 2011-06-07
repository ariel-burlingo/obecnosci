package obecnosci.ob.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Student;

@Stateless
public class GrupaManager {

	@PersistenceContext
	EntityManager em;

	public void dodajGrupe(String informacje) {
		Grupa grupa = new Grupa();
		grupa.setInformacje(informacje);
		em.persist(grupa);
	}

	public void usunGrupe(long idGrupy) {
		Grupa grupa = em.getReference(Grupa.class, idGrupy);
		em.remove(grupa);
	}

	public void edytujGrupe(Long id, String informacje) {
		Grupa grupa = em.getReference(Grupa.class, id);
		grupa.setInformacje(informacje);
		em.merge(grupa);
	}

	public List<Grupa> pobierzWszystkie() {
		return em.createQuery("from Grupa").getResultList();
	}
	
	public void przypiszListeDoGrupy(long grupaId, List<Student> studenty){
		Grupa grupa = em.getReference(Grupa.class, grupaId);
	
		Iterator<Student> istud = studenty.iterator();
		while(istud.hasNext()){
			Student student = em.getReference(Student.class, istud.next().getId());
					
			List<Grupa> grupy = student.getGrupy();
			grupy.add(grupa);
			student.setGrupy(grupy);
			
			List<Student> studenci = grupa.getStudenci();
			studenci.add(student);
			grupa.setStudenci(studenci);
			
			em.merge(student);
			em.merge(grupa);
		}
	}
	public void wypiszStudentaZGrupy(long grupaId, Student student){
		// TODO	
	}
	
	public List<Student> pobierzStudentow(){
		System.out.println("GRUPA MANAGER POBIERZ STUDENTOW");
		return em.createQuery("from Student").getResultList();
		
		//List<Student> studenci = new ArrayList<Student>();
	//	List<Student> deep = new ArrayList<Student>();
		// petla ktora kopiuje z referencji do stworzonego obiektu wiersz po wierszu
//		studenci = em.find(Grupa.class, id).getStudenci();
//		Iterator<Student> iter = studenci.iterator();
//		while(iter.hasNext()){
////			deep.add(iter.next());
//		}
//		return deep;
		//return 
	}
	
	
}
