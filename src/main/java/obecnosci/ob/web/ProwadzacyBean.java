package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.service.ProwadzacyManager;
import obecnosci.ob.service.PrzedmiotManager;


@SessionScoped
@Named
public class ProwadzacyBean implements Serializable {

	private static final long serialVersionUID = -8934788015170982221L;

	@Inject
	PrzedmiotManager przedmiotManager;
	@Inject
	ProwadzacyManager prowadzacyManager;
	
	
	//ZMIENNE
	
	private long   id=0;
	private String imie;
	private String nazwisko;
	private String daneKontaktowe;
	private String stronaDomowa;

	
	//SETTERY I GETTERY
	
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
	
	
	//METODY WLASNE
	
	public List<Prowadzacy> getWszystkichProwadzacych(){
		return prowadzacyManager.pobierzWszystkie();
		
	}
	
	
	//AKCJE
	
	public String dodajProwadzacego(){
		prowadzacyManager.dodajProwadzacego(imie, nazwisko, daneKontaktowe, stronaDomowa);
		return "";
	}
	
	
}
