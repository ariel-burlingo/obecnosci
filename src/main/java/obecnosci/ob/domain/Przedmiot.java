package obecnosci.ob.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
public class Przedmiot {
	@Id
	@GeneratedValue
	private long id;
	private String dane;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDane() {
		return dane;
	}
	public void setDane(String dane) {
		this.dane = dane;
	}
	
	
	
}
