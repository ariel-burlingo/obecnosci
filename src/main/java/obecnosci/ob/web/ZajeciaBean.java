package obecnosci.ob.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.service.ZajeciaManager;

@SessionScoped
@Named
public class ZajeciaBean implements Serializable{

	@Inject
	ZajeciaManager zajeciaManager;
	
	private static final long serialVersionUID = -7102250578153538924L;
	
	private long prowadzacyId = 0;
	private long przedmiotId = 0;
	private long grupaId = 0;
	private int ile = 0;
	
	private Date pierwszeZajecia = new Date();
	private Date nowaData = new Date();
	
	// DATATABLE BACKEND
	private HtmlDataTable zajecia;
	
	
	public long getProwadzacyId() {
		return prowadzacyId;
	}
	public void setProwadzacyId(long prowadzacyId) {
		this.prowadzacyId = prowadzacyId;
	}
	public int getIle() {
		return ile;
	}
	public void setIle(int ile) {
		this.ile = ile;
	}
	public Date getPierwszeZajecia() {
		return pierwszeZajecia;
	}
	public void setPierwszeZajecia(Date pierwszeZajecia) {
		this.pierwszeZajecia = pierwszeZajecia;
	}
	public long getPrzedmiotId() {
		return przedmiotId;
	}
	public void setPrzedmiotId(long przedmiotId) {
		this.przedmiotId = przedmiotId;
	}
	public long getGrupaId() {
		return grupaId;
	}
	public void setGrupaId(long grupaId) {
		this.grupaId = grupaId;
	}
	public Date getNowaData() {
		return nowaData;
	}
	public void setNowaData(Date nowaData) {
		this.nowaData = nowaData;
	}
	public HtmlDataTable getZajecia() {
		return zajecia;
	}
	public void setZajecia(HtmlDataTable zajecia) {
		this.zajecia = zajecia;
	}
	
	// WLASNE METODY
	
	public List<Zajecia> getWszystkieZajecia(){
		return zajeciaManager.pobierzWszystkie();
	}
	
	
	
	
	
	// AKCJE 
		
	public String zaplanuj(){
		zajeciaManager.zaplanujAutoStart(prowadzacyId, przedmiotId, grupaId, ile, pierwszeZajecia);
		return "";
	}
	
	public String zmienZajecia(){
		Zajecia instancja = (Zajecia) zajecia.getRowData();
		zajeciaManager.zmienCzasRozpoczecia(prowadzacyId, instancja.getId() , nowaData);
		return "";
	}
	
	public String reczneSterowanie(){
		// pobranie z HTML DataTable i rzutowanie
		Zajecia instancja = (Zajecia) zajecia.getRowData();
		zajeciaManager.zmienCzasRozpoczecia(prowadzacyId, instancja.getId() , new Date());
		return "";
	}
	
	public String usunZajecia(){
		Zajecia instancja = (Zajecia) zajecia.getRowData();
		zajeciaManager.usunZajecia(instancja.getId());
		return "";
	}
	

}
