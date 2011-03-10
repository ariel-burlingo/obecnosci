package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;


//import org.primefaces.examples.*;
import obecnosci.ob.service.GrupaManager;
import obecnosci.ob.service.PrzedmiotManager;
import obecnosci.ob.service.StudentManager;

import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;

@SessionScoped
@Named
public class StudentBean implements Serializable{
	
	private static final long serialVersionUID = -2381831113540936545L;

	@Inject
	StudentManager studentManager;
	@Inject
	PrzedmiotManager przedmiotManager;
	@Inject
	GrupaManager grupaManager;
	
	
	// DEBUGGER
	private String techinfo;
	
	// ZMIENNE
	private long id =0;
	private int index =0;
	private String imie="";
	private String nazwisko="";
	private String haslo="";
	private long grupaId=0;
	private long przedmiotId=0;
	
	// DATATABLE BACKEND
	private HtmlDataTable studenci;
	private HtmlDataTable przedmioty;
	
	
	
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
	
	public long getGrupaId() {
		return grupaId;
	}
	public void setGrupaId(long grupaId) {
		this.grupaId = grupaId;
	}
		
	public long getPrzedmiotId() {
		return przedmiotId;
	}
	public void setPrzedmiotId(long przedmiotId) {
		this.przedmiotId = przedmiotId;
	}
	// DATATABLE BACKEDN
	public HtmlDataTable getStudenci() {
		return studenci;
	}
	public void setStudenci(HtmlDataTable studenci) {
		this.studenci = studenci;
	}
	public HtmlDataTable getPrzedmioty() {
		return przedmioty;
	}
	public void setPrzedmioty(HtmlDataTable przedmioty) {
		this.przedmioty = przedmioty;
	}
	
	// METODY WLASNE
	
	
	public List<Student> getWszystkichStudentow(){
		return studentManager.pobierzWszystkich();
		
	}
	
	public List<Przedmiot> getWszystkiePrzedmioty(){
		return przedmiotManager.pobierzWszystkie();		
	}
	
	// AKCJE
	
	public String dodajStudenta(){
		studentManager.dodajStudenta(index, imie, nazwisko, haslo);
		return "";
	}
	
	public String zapiszNaPrzedmiot(){
		// pobranie z HTML DataTable i rzutowanie
		Student instancja = (Student) studenci.getRowData();
		studentManager.zapiszNaPrzedmiot(instancja, przedmiotId);
		return "";
	}
	
	public String przypiszDoGrupy(){
		// pobranie z HTML DataTable i rzutowanie
		Student instancja = (Student) studenci.getRowData();
		studentManager.przypiszDoGrupy(instancja, grupaId);
		return "";
	}
	
	

}
