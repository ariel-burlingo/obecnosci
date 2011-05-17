package obecnosci.ob.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;

import org.hibernate.validator.util.GetMethods;
import org.primefaces.model.DualListModel;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;
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
	
	private long id=0;
	private long przedmiotId=0;
	public long getPrzedmiotId() {
		return przedmiotId;
	}
	public void setPrzedmiotId(long przedmiotId) {
		this.przedmiotId = przedmiotId;
	}

	private String imie;
	private String nazwisko;
	private String login;
	private String daneKontaktowe;
	private String stronaDomowa;
	private String password;
	private HtmlDataTable prowadzacy;
//
	private List<Prowadzacy> prowadzacych;
	
	
	
	
	
	public long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	
	public List<Przedmiot> getMojePrzedmioty(){
		return prowadzacyManager.pobierzMoje(id);
	}
	//NEW
	public List<Zajecia> getMojeZajecia(){
		return prowadzacyManager.pobierzMojeZajecia(id); 
		//return prowadzacyManager.pobierzMoje(id);
	}
	//
	
	
	
	//AKCJE
	
	public String dodajProwadzacego(){
		prowadzacyManager.dodajProwadzacego(imie, nazwisko, login, daneKontaktowe, stronaDomowa, password);
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
	
	public String przypiszDoPrzedmiotu(){
		//Prowadzacy instancja = (Prowadzacy) prowadzacy.getRowData();
		przedmiotManager.przypiszPrzedmiotDoProwadzacego(id, przedmiotId);
		return "";
	}
	
	public String wypiszZPrzedmiotu(){
		//Prowadzacy instancja = (Prowadzacy) prowadzacy.getRowData();
		przedmiotManager.wypiszProwadzacegoZPrzedmiotu(id, przedmiotId);
		return "";
	}
	
	public String zaloguj(){
		Prowadzacy prowadzacy = prowadzacyManager.zaloguj(login, password);
		if (prowadzacy.getId()>0){
			this.id = prowadzacy.getId();
			this.imie = prowadzacy.getImie();
			this.login = prowadzacy.getLogin();
			this.nazwisko = prowadzacy.getNazwisko();
			this.password = prowadzacy.getPassword();
			
		} else {
			this.id = 0;
			this.imie = "niezalogowany";
			this.login = "niezalogowany";
			this.nazwisko = "niezalobgowany";
			this.password = "STFU";
		}
		
		return "";
	}
	
}
