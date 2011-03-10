package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

//import org.primefaces.examples.*;
import obecnosci.ob.service.StudentManager;

import obecnosci.ob.domain.Student;

@SessionScoped
@Named
public class StudentBean implements Serializable{
	
	private static final long serialVersionUID = -2381831113540936545L;

	@Inject
	StudentManager studentManager;
	
	// DEBUGGER
	private String techinfo;
	
	// ZMIENNE
	private long id =0;
	private int index =0;
	private String imie="";
	private String nazwisko="";
	private String haslo="";
	
	// DATATABLE BACKEDN
	
	
	
	// SETTERY i GETTERY
	public String getTechinfo() {
		return techinfo;
	}
	public void setTechinfo(String techinfo) {
		this.techinfo = techinfo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	
	// METODY WLASNE
	
	public List<Student> getWszystkichStudentow(){
		return studentManager.pobierzWszystkich();
		
	}
	
	// AKCJE
	
	public String dodajStudenta(){
		studentManager.dodajStudenta(index, imie, nazwisko, haslo);
		return "";
	}
	
	

}
