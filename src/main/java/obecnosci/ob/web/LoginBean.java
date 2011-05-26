package obecnosci.ob.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.omg.CORBA.Request;

import obecnosci.ob.domain.Admin;
import obecnosci.ob.domain.Prowadzacy;
import obecnosci.ob.domain.Student;
import obecnosci.ob.service.AdminManager;
import obecnosci.ob.service.ProwadzacyManager;
import obecnosci.ob.service.StudentManager;
@SessionScoped
@Named
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -6360756516892427468L;
private String typ;
private String pole1;
private String pole2;

@Inject
AdminManager adminManager;
@Inject
StudentManager studentManager;
@Inject
ProwadzacyManager prowadzacyManager;
@Inject
StudentBean studentBean;
@Inject
ProwadzacyBean prowadzacyBean;
@Inject
AdminBean adminBean;

@SessionScoped
@Named
public String zaloguj(){
	
	if (typ=="0"){
		adminBean.setLogin(pole1);
		adminBean.setHaslo(pole2);
		adminBean.zaloguj();
		return "AdminHome";
	}
	if (typ.equalsIgnoreCase("1")){
		prowadzacyBean.setLogin(pole1);
		prowadzacyBean.setPassword(pole2);
		prowadzacyBean.zaloguj();
		return "ProwadzacyHome";
	}
	if (typ=="2"){
		studentBean.setIndex(Integer.parseInt(pole1));
		studentBean.setHaslo(pole2);
		studentBean.zaloguj();
		return "StudentHome";
	}
	return "Bledne";
	
	
}

public String getPole1() {
	return pole1;
}

public void setPole1(String pole1) {
	this.pole1 = pole1;
}

public String getPole2() {
	return pole2;
}

public void setPole2(String pole2) {
	this.pole2 = pole2;
}

public String getTyp() {
	return typ;
}

public void setTyp(String typ) {
	this.typ = typ;
}

}
