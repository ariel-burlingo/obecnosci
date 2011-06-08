package obecnosci.ob.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.service.ObecnosciManager;
import obecnosci.ob.service.ZajeciaManager;
import obecnosci.ob.utils.StudentZajecia;
import obecnosci.ob.web.ProwadzacyBean;

@SessionScoped
@Named
public class ExportBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ZajeciaManager zajeciaManager;
	
	@Inject
	ObecnosciManager obecnosciManager;
	
	@Inject
	ProwadzacyBean prowadzacyBean;
	
	private List<StudentZajecia> studentZajecia;
	
	public List<StudentZajecia> getStudentZajecia() {
		return studentZajecia;
	}

	public void setStudentZajecia(List<StudentZajecia> studentZajecia) {
		this.studentZajecia = studentZajecia;
	}

	private String[] Daty;
	
	public String[] getDaty(){
		return Daty;
	}
	
	public String[] getPobierz(){
		
		try{
			studentZajecia = new ArrayList<StudentZajecia>();
			System.out.println("WESZLEM DO BEANA");
			System.out.println("ID PRO : "+prowadzacyBean.getId());
			System.out.println("PRZEDM : "+prowadzacyBean.getWybranyPrzedmiot());
			System.out.println("GRUPA  : "+prowadzacyBean.getWybranaGrupaId());
			studentZajecia = obecnosciManager.daneDoRaportu(prowadzacyBean.getWybranyPrzedmiot().getId(), prowadzacyBean.getId(), prowadzacyBean.getWybranaGrupaId());
			Daty = studentZajecia.get(0).getDaty();
		} 
		catch(NullPointerException e){
			studentZajecia = new ArrayList<StudentZajecia>();
		}
		catch(IndexOutOfBoundsException e){
			studentZajecia = new ArrayList<StudentZajecia>();
		}
		
		//List<String>output = new ArrayList<String>();
		//List<Zajecia> zajU = zajeciaManager.pobierzZajeciaUnikalne(1, 1, 1);
		//List<StudentZajecia> stdZ = obecnosciManager.daneDoRaportu(1, 1, 1);
		/*Iterator<StudentZajecia> iter = stdZ.iterator();
		while(iter.hasNext()){
			StudentZajecia akt = iter.next();
			String obecnosci = akt.getObecnosci().toString();
			output.add(akt.getStudent().getNazwisko()+" "+obecnosci);
		}*/
		return Daty;
	}

	
}
