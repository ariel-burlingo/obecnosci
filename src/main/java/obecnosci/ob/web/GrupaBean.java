package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Grupa;

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
	
	//ZMIENNE
	
	private long id=0;
	private String informacje;
	private List<Grupa> grupy;
	
	
	//SETTERY i GETTERY
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
	
	//METODY WLASNE
	
	public List<Grupa> getWszystkieGrupy(){
		return grupaManager.pobierzWszystkie();
		
	}
	
	//AKCJE
	
	public String dodajGrupe(){
		grupaManager.dodajGrupe(informacje);
		return "";
	}
	/*
	public String usunGrupe(){
	grupaManager.usunGrupe(instancja);
	return "";
		
	}
	*/
	
	
	
	
	
}