package obecnosci.ob.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.validator.util.GetMethods;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import obecnosci.ob.domain.Grupa;
import obecnosci.ob.domain.Obecnosci;
import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.service.ObecnosciManager;
import obecnosci.ob.service.ProwadzacyManager;
import obecnosci.ob.service.PrzedmiotManager;
import obecnosci.ob.service.ZajeciaManager;
import obecnosci.ob.utils.StudentZajecia;


@SessionScoped
@Named
public class ProwadzacyBean implements Serializable {

	private static final long serialVersionUID = -8934788015170982221L;

	@Inject
	PrzedmiotManager przedmiotManager;
	@Inject
	ProwadzacyManager prowadzacyManager;
	@Inject
	ObecnosciManager obecnosciManager;
	@Inject
	ZajeciaManager zajeciaManager;
	
	
	
	
	//ZMIENNE
		
	private long id=0;
	private long przedmiotId=0;
	private long grupaId=0;
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
	private long wybranaGrupaId;
	public long getWybranaGrupaId() {
		return wybranaGrupaId;
	}
	public void setWybranaGrupaId(long wybranaGrupaId) {
		this.wybranaGrupaId = wybranaGrupaId;
	}



	private Zajecia wybraneZajecia = new Zajecia();
	private Obecnosci wybranaObecnosc = new Obecnosci();
	private Student wybranyStudent = new Student();
	private Przedmiot wybranyPrzedmiot = new Przedmiot();
	public Przedmiot getWybranyPrzedmiot() {
		return wybranyPrzedmiot;
	}
	public void setWybranyPrzedmiot(Przedmiot wybranyPrzedmiot) {
		this.wybranyPrzedmiot = wybranyPrzedmiot;
	}

	private List<Obecnosci> obecnosciNaWybranych;
	
	

	public Student getWybranyStudent() {
		return wybranyStudent;
	}
	public void setWybranyStudent(Student wybranyStudent) {
		this.wybranyStudent = wybranyStudent;
	}
	public Obecnosci getWybranaObecnosc() {
		return wybranaObecnosc;
	}
	public void setWybranaObecnosc(Obecnosci wybranaObecnosc) {
		this.wybranaObecnosc = wybranaObecnosc;
	}
	public List<Obecnosci> getObecnosciNaWybranych() {
		return obecnosciNaWybranych;
	}
	public void setObecnosciNaWybranych(List<Obecnosci> obecnosciNaWybranych) {
		this.obecnosciNaWybranych = obecnosciNaWybranych;
	}

	private boolean obecny;
	
	public boolean getObecny() {
		return obecny;
	}
	public void setObecny(boolean obecny) {
		this.obecny = obecny;
	}
	public Zajecia getWybraneZajecia() {
		return wybraneZajecia;
	}
	public void setWybraneZajecia(Zajecia wybraneZajecia) {
		this.wybraneZajecia = wybraneZajecia;
	}

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
	
	public List<Zajecia> getMojeAktualnieOdbywajaceSieZajecia(){
		return prowadzacyManager.pobierzMojeAktualnieOdbywajaceSieZajecia(id);
	}
	
	public List<Obecnosci> getObecnosciNaTychZajeciach(){
		return obecnosciManager.pobierzObecnosciZZajec(wybraneZajecia.getId());
	}
	
	public List<Zajecia> getZajeciaNaKtorychStudentBylNieObecny(){
		return zajeciaManager.pobierzDlaStudentaZajeciaNaKtorychBylNieObecny(wybranyStudent.getId(), prowadzacyManager.pobierzMoje(id));
	}
	
	public List<Student> getMoiStudenci(){
		return prowadzacyManager.pobierzMoichStudentow(id);		
	}
	
	
	
	
	
	
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
	
	public String potwierdzajObecnosci(ActionEvent event) {
		System.out.println("cos sie dzieje");
	    this.setWybraneZajecia((Zajecia)event.getComponent().getAttributes().get("aktOdbZaj"));
	    FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navHandler = context.getApplication().getNavigationHandler();
	    navHandler.handleNavigation(context, null, "potwierdzOb");
	    return "";	    
	}
	
	public String usprawiedliwiajNieObecnosci(ActionEvent event) {
		System.out.println("cos sie dzieje - selecty  przejscie do usprawiedliwiania nieobecnosci");
	    this.setWybranyStudent((Student)event.getComponent().getAttributes().get("aktOdbZaj"));
	    FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navHandler = context.getApplication().getNavigationHandler();
	    navHandler.handleNavigation(context, null, "usprawiedliwOb");
	    return "";	    
	}
	
	// AJAX
	public void potwierdzWybranaObecnosc(SelectEvent event) {
		System.out.println("cos sie dzieje - probuje potwierdzac");
	    Obecnosci ob = (Obecnosci)event.getObject();
	    obecnosciManager.zatwierdzObecnosci(ob.getId());
	    FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("Zatwierdzono", "Indeks: "+ob.getStudent().getIndex()+"<br />"+ob.getStudent().getImie()+" "+ob.getStudent().getNazwisko()));  
	}
	
	
	
	// AJAX
	public void usprawiedliwWybranaNieObecnosc(SelectEvent event) {
		System.out.println("cos sie dzieje - probuje usprawiedliwiac");
	    Zajecia zaj = (Zajecia)event.getObject();
	    obecnosciManager.usprawiedliwNieObecnosc(wybranyStudent.getId(), zaj.getId());
	    FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("Usprawiedliwiono",zaj.getData().toString()+ "<br />Indeks: "+wybranyStudent.getIndex()+"<br />"+wybranyStudent.getImie()+" "+wybranyStudent.getNazwisko()));  
	}
	// AJAX
	public void reczneSterowanieZajeciami(){
		System.out.println("cos sie dzieje - proba recznego sterowania");
	    zajeciaManager.zmienCzasRozpoczecia(id, wybraneZajecia.getId() , new Date());
	    FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("[!] R�czne Sterowanie","Zaj�cia z "+wybraneZajecia.getPrzedmiot().getNazwa()+ "<br />Gr: "+wybraneZajecia.getGrupa().getInformacje()+"<br />Zosta�y rozpocz�te"));  
	
	}
	
	// AJAX
	public void zmienCzasRozpoczecia(){
		System.out.println("cos sie dzieje - proba zmiany czasu rozpoczecia");
	    zajeciaManager.zmienCzasRozpoczecia(id, wybraneZajecia.getId() , wybraneZajecia.getData());
	    FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("Pomy�lnie zmieniono","Czas rozpoczecia zaj��: "+wybraneZajecia.getData().toString()));  
	
	}
	
	
	public String przejdzDoRaportu() {
		System.out.println("cos sie dzieje - zapisalem ktora grupa");
		FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navHandler = context.getApplication().getNavigationHandler();
	    navHandler.handleNavigation(context, null, "raport");
	    return "";
	    }
	
	
	public String zaloguj(String login,String password){		
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
