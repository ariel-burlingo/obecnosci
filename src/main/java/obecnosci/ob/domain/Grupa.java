package obecnosci.ob.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="grupa.all", query ="from Grupa")
public class Grupa {
	
	@Id
	@GeneratedValue
	private long id;
	private List<Student> studenci;
	private String informacje;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Student> getStudenci() {
		return studenci;
	}
	public void setStudenci(List<Student> studenci) {
		this.studenci = studenci;
	}
	public String getInformacje() {
		return informacje;
	}
	public void setInformacje(String informacje) {
		this.informacje = informacje;
	}
	
	
	
	
}