package are.edu.unju.fi.tp4.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.model.Cliente;
import are.edu.unju.fi.tp4.service.IClienteService;
import are.edu.unju.fi.tp4.util.ListadoClientes;
@Service
@Qualifier("unImp")
public class ClienteServiceimp implements IClienteService{
	private List<Cliente> listadoClientes = ListadoClientes.clientes;
	@Autowired
	Cliente unCliente;
	@Override
	public void guardarCliente(Cliente unCliente) {
		LocalDate ultimaCompra=unCliente.getFechaUltimaCompra();
		LocalDate ahora=LocalDate.now();
		Period period=Period.between(ultimaCompra,ahora);
		System.out.println(period);
		
		listadoClientes.add(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return listadoClientes;
	}

	@Override
	public Cliente encontrarUnCliente(int dni,String email) {
		for (int i = 0; i < listadoClientes.size(); i++){
		    if ((listadoClientes.get(i).getNroDocumento() == dni)&&(listadoClientes.get(i).getEmail().equals(email))){
		    	unCliente = listadoClientes.get(i);
		    }
		}
		return unCliente;
	}
	
	@Override
	public void modificarCliente(Cliente clienteModificado) {
		int aux,aux2,aux5 = 0;
		String aux3,aux4;
		boolean band=false;
		aux2=clienteModificado.getEdad();
		aux4=clienteModificado.getEmail();
		for (int i = 0; i < listadoClientes.size() && band==false; i++){
			aux=listadoClientes.get(i).getEdad();
			aux3=listadoClientes.get(i).getEmail();
		    if ((aux == aux2)&&(aux4.equals(aux3))) {
		    	band=true;
		    	aux5=i;
		    }
		}
		for (int i = 0; i < listadoClientes.size(); i++){
		    if ((i == aux5)) {
		    	listadoClientes.set(i, clienteModificado);
		    }
		}
	}

	@Override
	public void eliminarCliente(int id) {
		for (int i = 0; i < listadoClientes.size(); i++){
			if((listadoClientes.get(i).getNroDocumento()==id)) {
				listadoClientes.remove(i);
			}
		}
	}

}
