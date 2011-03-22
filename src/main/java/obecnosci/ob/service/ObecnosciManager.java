package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import obecnosci.ob.domain.Obecnosci;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ObecnosciManager {

	@PersistenceContext
	EntityManager em;
	/*
	public void zapiszObecnosc(Student instancjaStudenta, Zajecia instancjaZajecLong id) {
		Student student = em.getReference(Student.class,
				instancjaStudenta.getId());
		//Zajecia zajecia = em.getReference(Zajecia.class, instancjaZajec.getId());
		Zajecia zajecia = new Zajecia();
		zajecia.setId(id);
		Obecnosci obecnosc = new Obecnosci();
		obecnosc.setStudent(student);
		obecnosc.setZajecia(zajecia);
		obecnosc.setTyp((short)-1);
		em.persist(obecnosc);
		
	}
	*/
public void zapiszObecnosc(Long IdStudenta,Long IdZajec){
	Obecnosci obecnosc = new Obecnosci();
	Student student = new Student();
	Zajecia zajecia = new Zajecia();
	student.setId(IdStudenta);
	zajecia.setId(IdZajec);
	obecnosc.setStudent(student);
	obecnosc.setZajecia(zajecia);
	obecnosc.setTyp((short)-1);
	em.persist(obecnosc);
}


	public void zatwierdzObecnosci(Long IdObecnosci) {
		//Obecnosci obecnosc = em.getReference(Obecnosci.class,instancjaObecnosci.getId());
		Obecnosci obecnosc = new Obecnosci();
		obecnosc.setId(IdObecnosci);
		obecnosc.setTyp((short)1);
		em.merge(obecnosc);
	}
	
	public void usprawedliwN(Obecnosci instancjaObecnosci){
		Obecnosci obecnosc = em.getReference(Obecnosci.class,instancjaObecnosci.getId());
		obecnosc.setTyp((short)0);
		em.merge(obecnosc);
	}
	public List<Obecnosci> pobierzWszystkie(){
		return em.createQuery("from Obecnosci").getResultList();
	}
}
