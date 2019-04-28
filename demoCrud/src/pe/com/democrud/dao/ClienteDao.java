package pe.com.democrud.dao;

import java.util.List;

import pe.com.democrud.bean.Cliente;



public interface ClienteDao {

	List<Cliente> listaCliente(Cliente cliente);	
	void insertarCliente(Cliente cliente);
	void actualizarCliente(Cliente cliente);
	
}
