package obecnosci.ob.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.web.AdminBean;
import obecnosci.ob.web.StudentBean;
import obecnosci.ob.web.ProwadzacyBean;

@SessionScoped
@Named
@ManagedBean
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -6360756516892427468L;
private String typ;
private String pole1;
private String pole2;


@Inject
StudentBean studentBean;
@Inject
ProwadzacyBean prowadzacyBean;
@Inject
AdminBean adminBean;

public String zaloguj(){
	menu1=false;
	if (typ.equalsIgnoreCase("0")){
		adminBean.setLogin(pole1);
		adminBean.setHaslo(pole2);
		adminBean.zaloguj();
		menu1=true;
		return "adminHome";
	}
	if (typ.equalsIgnoreCase("1")){
	menu2=false;
		prowadzacyBean.setLogin(pole1);
		prowadzacyBean.setPassword(pole2);
		prowadzacyBean.zaloguj();
		menu2=true;
		return "prowadzacypf";
	}
	if (typ.equalsIgnoreCase("2")){
	menu3=false;
		studentBean.setIndex(Integer.parseInt(pole1));
		studentBean.setHaslo(pole2);
		studentBean.zaloguj();
		menu3=true;
		return "studentpf";
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

public boolean ismenu1(){
	return menu1;
}

private boolean menu1;

public boolean ismenu2(){
	return menu2;
}

private boolean menu2;

public boolean ismenu3(){
	return menu3;
}
private boolean menu3;


}
