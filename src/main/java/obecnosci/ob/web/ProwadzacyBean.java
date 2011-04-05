package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;
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
	private String password;
	private HtmlDataTable prowadzacy;
//
	private List<Prowadzacy> prowadzacych;
	//
	
	//SETTERY I GETTERY
	
	public long getId() {
		return id;
	}
	public List<Prowadzacy> getProwadzacych() {
		return prowadzacych;
	}
	public void setProwadzacych(List<Prowadzacy> prowadzacych) {
		this.prowadzacych = prowadzacych;
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
	
	public HtmlDataTable getProwadzacy() {
		return prowadzacy;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//METODY WLASNE
	
	public void setProwadzacy(HtmlDataTable prowadzacy) {
		this.prowadzacy = prowadzacy;
	}
	public List<Prowadzacy> getWszystkichProwadzacych(){
		return prowadzacyManager.pobierzWszystkie();
		
	}
	
	
	//AKCJE
	
	public String dodajProwadzacego(){
		prowadzacyManager.dodajProwadzacego(imie, nazwisko, daneKontaktowe, stronaDomowa, password);
		return "";
	}
	public String modyfikujProwadzacego(){
		Prowadzacy instancja = (Prowadzacy) prowadzacy.getRowData();
		prowadzacyManager.modyfikujProwadzacego(instancja.getId(), daneKontaktowe, stronaDomowa);
		return "";
	}
	public String usunProwadzacego(){
		Prowadzacy instancja = (Prowadzacy) prowadzacy.getRowData();
		prowadzacyManager.usunProwadzacego(instancja);
		return"";
	}
	
}
