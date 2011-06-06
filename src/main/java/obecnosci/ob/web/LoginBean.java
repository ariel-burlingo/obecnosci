package obecnosci.ob.web;

import java.awt.Event;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.web.AdminBean;
import obecnosci.ob.web.StudentBean;
import obecnosci.ob.web.ProwadzacyBean;

@SessionScoped
@Named
@ManagedBean
public class LoginBean implements Serializable{

	
	
	@Inject
	StudentBean studentBean;
	@Inject
	ProwadzacyBean prowadzacyBean;
	@Inject
	AdminBean adminBean;
	
	
	private static final long serialVersionUID = -6360756516892427468L;
	private String typ;
	private String pole1;
	private String pole2;

	private boolean menu1=false;
	private boolean menu2=false;
	private boolean menu3=false;
	
	// SETT I GETT
	
	public boolean ismenu1(){
		return menu1;
	}
	public boolean ismenu2(){
		return menu2;
	}
	public boolean ismenu3(){
		return menu3;
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
	
	// METODY i AKCJE
	
	public boolean iswyloguj(){
		menu1=false;
		menu2=false;
		menu3=false;
		return false;

	}

	public void logout(){
		menu1=false;
		menu2=false;
		menu3=false;
		adminBean.setLogin("");
		adminBean.setHaslo("");
		prowadzacyBean.setLogin("");
		prowadzacyBean.setPassword("");
		studentBean.setIndex(0);
		studentBean.setHaslo("");
		
		FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navHandler = context.getApplication().getNavigationHandler();
	    navHandler.handleNavigation(context, null, "zaloguj");
	    
	}
	
	public String zaloguj(){
		String whereTo="";
		menu1=false;
		menu2=false;
		menu3=false;
		if (typ.equalsIgnoreCase("0")){
			adminBean.setLogin(pole1);
			adminBean.setHaslo(pole2);
			adminBean.zaloguj(pole1,pole2);
			// logika czy zalogowanie sie powiodlo
			if(adminBean.getId()>0){
				menu1=true;
				whereTo="adminHome";
			} else {
				menu1=false;
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage("B³¹d logowania","Podano b³êdny login lub has³o"));
		        
		        
			
			}
		}
		if (typ.equalsIgnoreCase("1")){

			prowadzacyBean.setLogin(pole1);
			prowadzacyBean.setPassword(pole2);
			prowadzacyBean.zaloguj(pole1,pole2);
			// logika czy zalogowanie sie powiodlo
			if(prowadzacyBean.getId()>0){
				menu2=true;
				whereTo="prowadzacypf";
				System.out.println("zalogowano");
			} else {
				menu2=false;
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage("B³¹d logowania","Podano b³êdny login lub has³o"));
		        
		        
			}
		}
		if (typ.equalsIgnoreCase("2")){
			int nrIdx = 0;
			try{
				nrIdx = Integer.parseInt(pole1);
			}
			catch(NumberFormatException e){
				nrIdx=-1;
			}
			if(nrIdx>0){
				studentBean.setIndex(nrIdx);
				studentBean.setHaslo(pole2);			
				studentBean.zaloguj(nrIdx,pole2);
				if(studentBean.getId()>0){
					menu3=true;
					whereTo="studentpf";

				} else {
					menu3=false;
					FacesContext context = FacesContext.getCurrentInstance();  
			        context.addMessage(null, new FacesMessage("B³¹d logowania","Podano b³êdny login lub has³o"));

				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage("B³¹d logowania","Zaloguj siê u¿ywaj¹c numeru indeksu!"));			
			}
		}
		
		// redirection bo PF
		if(!whereTo.equalsIgnoreCase("")){
			System.out.println("Proba nawigacji");
			FacesContext context = FacesContext.getCurrentInstance();
		    NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		    navHandler.handleNavigation(context, null, whereTo);
		}
		return "Bledne";


	}



	


}
