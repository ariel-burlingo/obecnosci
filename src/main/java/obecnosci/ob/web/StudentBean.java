package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;


//import org.primefaces.examples.*;
import obecnosci.ob.service.GrupaManager;
import obecnosci.ob.service.ObecnosciManager;
import obecnosci.ob.service.PrzedmiotManager;
import obecnosci.ob.service.StudentManager;
import obecnosci.ob.service.ZajeciaManager;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

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
	@Inject
	ZajeciaManager zajeciaManager;
	@Inject
	ObecnosciManager obecnosciManager;
	
	
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
	private DataTable zajecia;
	
	public DataTable getZajecia() {
		return zajecia;
	}
	public void setZajecia(DataTable zajecia) {
		this.zajecia = zajecia;
	}

	private Zajecia wybraneZajecia;
	
	
	
	public Zajecia getWybraneZajecia() {
		return wybraneZajecia;
	}
	public void setWybraneZajecia(Zajecia wybraneZajecia) {
		this.wybraneZajecia = wybraneZajecia;
	}
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
	
	public List<Grupa> getGrupy(){
		return studentManager.pobierzGrupy(id);
	}
	
	public List<Zajecia> getTerazOdbywajaceSie(){
		return zajeciaManager.pobierzDlaStudentaTerazOdbywajaceSie(id);
		
	}
	
	// AKCJE
	
	public String dodajStudenta(){
		studentManager.dodajStudenta(index, imie, nazwisko, haslo);
		return "";
	}
	
	public String zapiszZmianyAjax(){
		studentManager.zmienDaneStudenta(id, imie, nazwisko, haslo);
		return "";
	}
	
	public void loadCurrentRequest(ActionEvent event) {
	      this.wybraneZajecia = (Zajecia)event.getComponent().getAttributes().get("rec");
	   }
	
	public String zapiszObecnosc(){
		//Zajecia instancja = (Zajecia)zajecia.getRowData();
		obecnosciManager.zapiszObecnosc(id,wybraneZajecia.getId());
		return "";
	}
	
	public String zaloguj(int index,String haslo){
		Student student = studentManager.zaloguj(index, haslo);
		if (student.getId()>0){
			this.id = student.getId();
			this.imie = student.getImie();
			this.nazwisko = student.getNazwisko();
			this.haslo = student.getHaslo();
			
		} else {
			this.id = 0;
			this.imie = "niezalogowany";
			this.nazwisko = "niezalobgowany";
			this.haslo = "STFU";
		}
		
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
