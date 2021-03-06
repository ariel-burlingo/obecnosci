package obecnosci.ob.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.service.PrzedmiotManager;

@SessionScoped
@Named
public class PrzedmiotBean implements Serializable {

	private static final long serialVersionUID = -5381754988369431123L;
	
	@Inject
	PrzedmiotManager przedmiotManager;
	
	@Inject
	GrupaBean grupaBean;
	
	//ZMIENNE
	
	private long id=0;
	private String nazwa;
	private String inneDane;
	private List<Przedmiot> przedmioty;
	private long wybranyPrzedmiotId; 
	
	
	//GETTERY I SETTERY
	
	
	public long getWybranyPrzedmiotId() {
		return wybranyPrzedmiotId;
	}

	public void setWybranyPrzedmiotId(long wybranyPrzedmiotId) {
		this.wybranyPrzedmiotId = wybranyPrzedmiotId;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getInneDane() {
		return inneDane;
	}
	
	public void setInneDane(String inneDane) {
		this.inneDane = inneDane;
	}
	
	public List<Przedmiot> getPrzedmioty() {
		return przedmioty;
	}
	
	public void setPrzedmioty(List<Przedmiot> przedmioty) {
		this.przedmioty = przedmioty;
	}
	
	
	//METODY WLASNE
	public List<Przedmiot> getWszystkiePrzedmioty(){
		
		return przedmiotManager.pobierzWszystkie();
		
	}
	
	
	//AKCJE
	
	public String dodajPrzedmiot(){
		przedmiotManager.dodajPrzedmiot(nazwa,inneDane);
		return "";
	}
	
	public void zapiszListeNaPrzedmiot(){
		List<Student> lista = Arrays.asList(grupaBean.getWybraneStudenty());
		przedmiotManager.zapiszListeNaPrzedmiot(lista,wybranyPrzedmiotId);
	}
	
}
