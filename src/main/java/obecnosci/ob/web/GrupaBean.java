package obecnosci.ob.web;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;

import obecnosci.ob.service.GrupaManager;
import obecnosci.ob.service.StudentManager;

@SessionScoped
@Named
public class GrupaBean implements Serializable {

	private static final long serialVersionUID = -2843227256866567877L;

	@Inject
	StudentManager studentManager;
	@Inject
	GrupaManager grupaManager;

	// ZMIENNE
	private long id = 0;
	private String informacje;
	
	
	private long wybranaGrupaId = 1;
	
	private long wybranyStudentId = 1;

	public long getWybranyStudentId() {
		return wybranyStudentId;
	}

	public void setWybranyStudentId(long wybranyStudentId) {
		this.wybranyStudentId = wybranyStudentId;
	}

	public long getWybranaGrupaId() {
		return wybranaGrupaId;
	}

	public void setWybranaGrupaId(long wybranaGrupaId) {
		this.wybranaGrupaId = wybranaGrupaId;
	}

	private List<Grupa> grupy;
	
	private Student[] wybraneStudenty;
	
	private Grupa wybranaGrupa = new Grupa();
	

	

	public Grupa getWybranaGrupa() {
		return wybranaGrupa;
	}

	public void setWybranaGrupa(Grupa wybranaGrupa) {
		this.wybranaGrupa = wybranaGrupa;
	}

	public Student[] getWybraneStudenty() {
		return wybraneStudenty;
	}

	public void setWybraneStudenty(Student[] wybraneStudenty) {
		this.wybraneStudenty = wybraneStudenty;
	}

	// HTML BACKEND
	private HtmlDataTable grupa;

	// SETTERY i GETTERY
	public StudentManager getStudentManager() {
		return studentManager;
	}

	public void setStudentManager(StudentManager studentManager) {
		this.studentManager = studentManager;
	}

	public GrupaManager getGrupaManager() {
		return grupaManager;
	}

	public void setGrupaManager(GrupaManager grupaManager) {
		this.grupaManager = grupaManager;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInformacje() {
		return informacje;
	}

	public void setInformacje(String informacje) {
		this.informacje = informacje;
	}

	public List<Grupa> getGrupy() {
		return grupy;
	}

	public void setGrupy(List<Grupa> grupy) {
		this.grupy = grupy;
	}

	public HtmlDataTable getGrupa() {
		return grupa;
	}

	public void setGrupa(HtmlDataTable grupa) {
		this.grupa = grupa;
	}

	// METODY WLASNE
	public List<Grupa> getWszystkieGrupy() {
		return grupaManager.pobierzWszystkie();

	}
	
	public List<Student> getWszyscyStudenci(){
		return studentManager.pobierzWszystkich();
		
	}
	public List<Student> getStudenci(){
		try{
		System.out.println(wybranaGrupa.getId());
		return grupaManager.pobierzStudentow(wybranaGrupa.getId());
		}
		catch(NullPointerException e){
			return new ArrayList<Student>();
		}	
		
	}
	

	// AKCJE
	public String dodajGrupe() {
		grupaManager.dodajGrupe(informacje);
		return "";
	}

	public String edytujGrupe() {
		Grupa instancja = (Grupa) grupa.getRowData();
		grupaManager.edytujGrupe(instancja.getId(), informacje);
		return "";
	}

	public String usunGrupe() {
		Grupa instancja = (Grupa) grupa.getRowData();
		grupaManager.usunGrupe(instancja.getId());
		return "";
	}
	
	public void przypiszListeDoGrupy(){
		List<Student> lista = Arrays.asList(wybraneStudenty);
		grupaManager.przypiszListeDoGrupy(wybranaGrupaId, lista);		
	}
	
	public void usunZGrupy(){
		System.out.println("Usuanie z grupy");
		System.out.println("ID GNOJA: "+wybranyStudentId);
		studentManager.wypiszZGrupy(wybranyStudentId, wybranaGrupa.getId());
	}
}
