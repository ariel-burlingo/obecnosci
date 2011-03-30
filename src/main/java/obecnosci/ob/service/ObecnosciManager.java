package obecnosci.ob.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Obecnosci;
import obecnosci.ob.domain.Obecnosci.Typ_Obecnosci;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

@Stateless
public class ObecnosciManager {

	@PersistenceContext
	EntityManager em;

	public void zapiszObecnosc(Long IdStudenta, Long IdZajec) {
		Obecnosci obecnosc = new Obecnosci();
		Student student = new Student();
		Zajecia zajecia = new Zajecia();
		student.setId(IdStudenta);
		zajecia.setId(IdZajec);
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

	public void usprawedliwN(Obecnosci instancjaObecnosci) {
		Obecnosci obecnosc = em.getReference(Obecnosci.class,
				instancjaObecnosci.getId());
		obecnosc.setTyp(Typ_Obecnosci.USPRAWIEDLIWIONA);
		em.merge(obecnosc);
	}

	public List<Obecnosci> pobierzWszystkie() {
		return em.createQuery("from Obecnosci").getResultList();
	}
}
