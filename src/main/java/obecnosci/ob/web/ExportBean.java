package obecnosci.ob.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.service.ObecnosciManager;
import obecnosci.ob.service.ZajeciaManager;
import obecnosci.ob.utils.StudentZajecia;
import obecnosci.ob.web.ProwadzacyBean;

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
	
	
	
	public List<String> getPobierz(){
		
		
		List<String>output = new ArrayList<String>();
		//List<Zajecia> zajU = zajeciaManager.pobierzZajeciaUnikalne(1, 1, 1);
		List<StudentZajecia> stdZ = obecnosciManager.daneDoRaportu(1, 1, 1);
		Iterator<StudentZajecia> iter = stdZ.iterator();
		while(iter.hasNext()){
			StudentZajecia akt = iter.next();
			String obecnosci[] = akt.getObecnosci();
			output.add(akt.getStudent().getNazwisko()+" "+obecnosci);
		}
		return output;
	}
	
}
