package obecnosci.ob.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Przedmiot {
	@Id
	@GeneratedValue
	private long id;
	private String nazwa;
	private String inneDane;
	
	@ManyToMany
	private List<Student> studenci = new ArrayList<Student>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getinneDane() {
		return inneDane;
	}
	public void setinneDane(String inneDane) {
		this.inneDane = inneDane;
	}
	public List<Student> getStudenci() {
		return studenci;
	}
	public void setStudenci(List<Student> studenci) {
		this.studenci = studenci;
	}
	
	
	
}
