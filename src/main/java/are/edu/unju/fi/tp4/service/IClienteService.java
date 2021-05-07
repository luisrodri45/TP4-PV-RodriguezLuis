package are.edu.unju.fi.tp4.service;
import java.util.List;

import are.edu.unju.fi.tp4.model.Cliente;
public interface IClienteService {
	public void guardarCliente(Cliente unCliente);
	public Cliente crearCliente();
	public List<Cliente> obtenerTodosClientes();
	public Cliente encontrarUnCliente(int dni,String email);
	public void modificarCliente(Cliente clienteModificado);
	public void eliminarCliente(int dni);
}
