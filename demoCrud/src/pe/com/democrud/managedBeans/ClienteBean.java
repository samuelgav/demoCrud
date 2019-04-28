package pe.com.democrud.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pe.com.democrud.bean.Cliente;
import pe.com.democrud.service.ClienteService;
import pe.com.democrud.utils.FacesUtils;





@Component
@Scope("session")
@Slf4j
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private @Getter @Setter Cliente cliente;
	
	private @Getter @Setter List<Cliente> listaCliente;
	
	private @Getter @Setter String filtro;
	
	private @Getter @Setter String nombres;
	private @Getter @Setter String apellidos;
	private @Getter @Setter String direccion;
	
	
	
	@Autowired
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
		cliente=new Cliente();
	}
	
	
	public void limpiarBusqueda() {
		filtro = null;
		listaCliente = new ArrayList<Cliente>();
	}
	
	public void buscar() {
		Cliente clBusq=new Cliente();
		if (filtro ==null || "".equals(filtro)) {
			clBusq.setNombres(null);
		}else {
			clBusq.setNombres(filtro.toUpperCase());
		}
		listaCliente=clienteService.listaCliente(clBusq);
	}
	
	public void cleanModal() {
		cliente=new Cliente();
		nombres=null;
		apellidos=null;
		direccion=null;
	}
	
	public void abrirDlg() {
		cleanModal();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("$('.scCliente').modal('show')");
		context.update("formMdCliente:pnlDatos");
	}
	
	public void cargarEditar(Cliente cu) {
		cliente = cu;
		nombres =cliente.getNombres();
		apellidos=cliente.getApellidos();
		direccion=cliente.getDireccion();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("$('.scCliente').modal('show')");
	}
	
	
	public boolean validaCampos() {
		boolean valida=true;
		if(nombres == null || "".equals(nombres)) {
			valida=false;
			FacesUtils.mostrarMensaje("Debe ingresar el nombre.", 2);
		}
		if(apellidos == null || "".equals(apellidos)) {
			valida=false;
			FacesUtils.mostrarMensaje("Debe ingresar los apellidos.", 2);
		}
		if(direccion == null || "".equals(direccion)) {
			valida=false;
			FacesUtils.mostrarMensaje("Debe ingresar la direccion.", 2);
		}
		
		return valida;
	}
	
	
	public void grabar() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		boolean valida = true;
		
		if (cliente != null) {

			if (cliente.getCodCliente() == null) {
				
				if(validaCampos()) {
					cliente.setNombres(nombres);
					cliente.setApellidos(apellidos);
					cliente.setDireccion(direccion);				
					valida = clienteService.insertarCliente(cliente);
					if (valida) {
						FacesUtils.mostrarMensaje("Cliente grabado correctamente", 3);
					} else {
						FacesUtils.mostrarMensaje("Ocurrió un error al grabar el Cliente", 1);
					}
					
					buscar();					
					context.execute("$('.scCliente').modal('hide')");
					context.update("frmCliente:panelBusqueda");
										
				}else {					
					context.update("formMdCliente:msgErrors");
				}				
				
			} else {
				
				if(validaCampos()) {
					cliente.setNombres(nombres);
					cliente.setApellidos(apellidos);
					cliente.setDireccion(direccion);
					valida = clienteService.actualizarCliente(cliente);
					if (valida) {
						FacesUtils.mostrarMensaje("Cliente actualizada correctamente", 3);
					} else {
						FacesUtils.mostrarMensaje("Ocurrió un error al actualizar el Cliente", 1);
					}
					
					buscar();					
					context.execute("$('.scCliente').modal('hide')");
					context.update("frmCliente:panelBusqueda");
					
				}else {
					context.update("formMdCliente:msgErrors");
				}
			}
		} else {
			valida = false;
			FacesUtils.mostrarMensaje("Ocurrió un error al intentar grabar el Cliente. Comuniquese con el administrador del sistema.", 1);
			buscar();			
			context.execute("$('.scCliente').modal('hide')");
			context.update("frmCliente:panelBusqueda");
		}
	}
	
	
}
