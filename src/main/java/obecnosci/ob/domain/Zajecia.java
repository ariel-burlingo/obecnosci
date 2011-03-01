package obecnosci.ob.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="zajecia.all", query ="from Zajecia")
public class Zajecia {
	@Id
	@GeneratedValue
	private long id;
	private String adnotacje;
	private Date data;
	private Prowadzacy prowadzacy;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdnotacje() {
		return adnotacje;
	}
	public void setAdnotacje(String adnotacje) {
		this.adnotacje = adnotacje;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Prowadzacy getProwadzacy() {
		return prowadzacy;
	}
	public void setProwadzacy(Prowadzacy prowadzacy) {
		this.prowadzacy = prowadzacy;
	}
	
	
	
	
}
