package obecnosci.ob.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
public class Student {
	
@Id
@GeneratedValue
private long id;
private int index;
private String imie;
private String nazwisko;
private String haslo;
@ManyToMany
private List<Grupa> grupy;
@ManyToMany
private List<Przedmiot> przedmioty;

public long getId() {
	return id;
}


public int getIndex() {
	return index;
}


public void setIndex(int index) {
	this.index = index;
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


public List<Przedmiot> getPrzedmioty() {
	return przedmioty;
}


public void setPrzedmioty(List<Przedmiot> przedmioty) {
	this.przedmioty = przedmioty;
}



}
