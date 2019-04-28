package pe.com.democrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.democrud.bean.Cliente;
import pe.com.democrud.dao.ClienteDao;
import pe.com.democrud.service.ClienteService;

@Service("clienteService")
@Qualifier("Facturacion")
@Slf4j
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public List<Cliente> listaCliente(Cliente cliente) {
		try {
			return clienteDao.listaCliente(cliente);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			clienteDao.insertarCliente(cliente);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			clienteDao.actualizarCliente(cliente);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
