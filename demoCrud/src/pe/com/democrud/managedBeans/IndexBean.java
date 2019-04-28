package pe.com.democrud.managedBeans;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Scope("session")
@Slf4j
public class IndexBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public String redirectHome() {
		return "pretty:inicio";
	}
	
	public String redirectCliente() {
		return "pretty:mntCliente";
	}
}
