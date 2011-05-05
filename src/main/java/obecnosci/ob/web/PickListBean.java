package obecnosci.ob.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.service.ProwadzacyManager;
import obecnosci.ob.service.PrzedmiotManager;

import org.primefaces.model.DualListModel;


@ApplicationScoped
@Named
public class PickListBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProwadzacyManager prowadzacyManager;
	@Inject
	ProwadzacyBean prowadzacyBean;
	
	private DualListModel<Przedmiot> przedmiots; 
	
	
	public void init(){
		
		List<Przedmiot> source = new ArrayList<Przedmiot>();
		List<Przedmiot> target = new ArrayList<Przedmiot>();//prowadzacyManager.pobierzMoje(prowadzacyBean.getId());
		List<Przedmiot> referencja = prowadzacyManager.pobierzMoje(prowadzacyBean.getId());
		
		if(referencja != null){
			Przedmiot p = new Przedmiot();
			p.setNazwa("COS WCZYTAL");
			p.setId(10);
			source.add(p);
		}
		Przedmiot t = new Przedmiot();
		
		t.setNazwa("TWOJA DUPA");
		t.setId(11);
		target.add(t);		
		przedmiots = new DualListModel<Przedmiot>(source, target);
	}

	
	
	
	// DUAL TABLE COSTAM
	
	
	//SETTERY I GETTERY
	public DualListModel<Przedmiot> getPrzedmiots() {
		return przedmiots;
	}
	public void setPrzedmiots(DualListModel<Przedmiot> przedmiots) {
		this.przedmiots = przedmiots;
	}
}
