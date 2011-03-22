package obecnosci.ob.web;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.service.ZajeciaManager;

@SessionScoped
@Named
public class ZajeciaBean implements Serializable{

	@Inject
	ZajeciaManager zajeciaManager;
	
	private static final long serialVersionUID = -7102250578153538924L;
	
	private long prowadzacyId = 0;
	private int ile = 0;
	private Date pierwszeZajecia = new Date();
	
	
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
	
	
	// ZKCJE 
	
	public String zaplanuj(){
		zajeciaManager.zaplanujAutoStart(prowadzacyId, ile, pierwszeZajecia);
		return "";
	}
	

}
