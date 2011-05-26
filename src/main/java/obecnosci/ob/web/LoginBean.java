package obecnosci.ob.web;

import java.io.Serializable;

import obecnosci.ob.domain.Admin;

public class LoginBean implements Serializable{
private String pole1;
private String pole2;
@Inject

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



	
	
}