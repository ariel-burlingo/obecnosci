package obecnosci.ob.web;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Admin;
import obecnosci.ob.service.AdminManager;



@SessionScoped
@Named
public class AdminBean implements Serializable{
	@Inject
	AdminManager adminManager;
	@Inject 
	
	private static final long serialVersionUID = -6361273594630157066L;
	
	
	//ZMIENNE
	private long id =0;
	private String login="";
	private String haslo="";
	
	public String zaloguj(){
		Admin admin = adminManager.zaloguj(login, haslo);
		if (admin.getId()>0){
			this.id = admin.getId();
			this.haslo = admin.getHaslo();
			
		} else {
			this.id = 0;
			this.haslo = "STFU";
		}
		
		return "kupa";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	
}
