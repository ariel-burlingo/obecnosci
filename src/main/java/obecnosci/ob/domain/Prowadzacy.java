package obecnosci.ob.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
public class Prowadzacy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
private String imie;
private String nazwisko;
private String daneKontaktowe;
private String stronaDomowa;
@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
private List<Przedmiot> przedmioty;

public List<Przedmiot> getPrzedmioty() {
	return przedmioty;
}
public void setPrzedmioty(List<Przedmiot> przedmioty) {
	this.przedmioty = przedmioty;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getImie() {
	return imie;
}
public void setImie(String imie) {
	this.imie = imie;
}
public String getNazwisko() {
	return nazwisko;
}
public void setNazwisko(String nazwisko) {
	this.nazwisko = nazwisko;
}
public String getDaneKontaktowe() {
	return daneKontaktowe;
}
public void setDaneKontaktowe(String daneKontaktowe) {
	this.daneKontaktowe = daneKontaktowe;
}
public String getStronaDomowa() {
	return stronaDomowa;
}
public void setStronaDomowa(String stronaDomowa) {
	this.stronaDomowa = stronaDomowa;
}


}
