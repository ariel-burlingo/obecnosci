package obecnosci.ob.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Obecnosci {
	@Id
	@GeneratedValue
	private long id;
	Student student;
	Zajecia zajecia;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Zajecia getZajecia() {
		return zajecia;
	}
	public void setZajecia(Zajecia zajecia) {
		this.zajecia = zajecia;
	}

	
	
}
