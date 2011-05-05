package obecnosci.ob.web;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import obecnosci.ob.domain.Przedmiot;
import obecnosci.ob.service.PrzedmiotManager;

public class PrzedmiotConverter implements Converter{
	@Inject
	PrzedmiotManager przedmiotManager;
	
		
		public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
	        return przedmiotManager.pobierzPoId(Long.parseLong(key));
	    }

		public String getAsString(FacesContext context, UIComponent component, Object value){
			return String.valueOf(((Przedmiot) value).getId());
		}

}
