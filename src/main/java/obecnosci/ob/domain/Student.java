package obecnosci.ob.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="student.all", query ="from Student")
public class Student {
	
@Id
@GeneratedValue
private long id;
private String imie;
private String nazwisko;
private String haslo;
private List<Grupa> grupy;
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
public String getHaslo() {
	return haslo;
}
public void setHaslo(String haslo) {
	this.haslo = haslo;
}
public List<Grupa> getGrupy() {
	return grupy;
}
public void setGrupy(List<Grupa> grupy) {
	this.grupy = grupy;
}


}