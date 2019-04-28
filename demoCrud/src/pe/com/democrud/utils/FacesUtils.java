package pe.com.democrud.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacesUtils {

	public static void mostrarMensaje(String texto, int tipo) {
		switch (tipo) {
		case 1:
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, ""));
			break;
		case 2:
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, texto, ""));
			break;
		case 3:
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, texto, ""));
			break;
		case 4:
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, texto, ""));
			break;
		default:
			break;
		}
	}
	
}
