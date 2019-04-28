package pe.com.democrud.service;

import java.util.List;

import pe.com.democrud.bean.Cliente;

public interface ClienteService {

	List<Cliente> listaCliente(Cliente cliente);	
	boolean insertarCliente(Cliente cliente);
	boolean actualizarCliente(Cliente cliente);
}
