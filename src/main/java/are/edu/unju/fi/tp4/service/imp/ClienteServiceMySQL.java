package are.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.controller.ClienteController;
import are.edu.unju.fi.tp4.model.Cliente;
import are.edu.unju.fi.tp4.repository.IclienteDAO;
import are.edu.unju.fi.tp4.service.IClienteService;
@Service
@Qualifier("impmysql")
public class ClienteServiceMySQL implements IClienteService{
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	@Autowired
	Cliente unCliente;
	
	@Autowired
	IclienteDAO clienteDAO;
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		String pw = unCliente.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unCliente.setPassword(bCryptPasswordEncoder.encode(pw));
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarUnCliente(int dni, Integer id) throws Exception{
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: encontrando cliente: "+id);
		return clienteDAO.findById(id).orElseThrow(()->new Exception("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) throws Exception{
		//tuve problemas encontrando la id del cliente por eso añadi esta traza
		LOGGER.info("METHOD: Modificando cliente: "+clienteModificado.getIdCliente());
		//al final el valor me da null
		Cliente clienteAModificar = clienteDAO.findById(clienteModificado.getIdCliente()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(clienteModificado, clienteAModificar);
		clienteDAO.save(clienteAModificar);
	}
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}
	@Override
	public void eliminarCliente(int dni) throws Exception{
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}

}
